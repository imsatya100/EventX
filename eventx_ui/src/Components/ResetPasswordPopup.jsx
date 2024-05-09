import React, { useState } from 'react';
import { Alert, Form, FormGroup, Button } from 'react-bootstrap';
import { MDBInput } from 'mdb-react-ui-kit';
import UserService from '../service/UserService';
const ResetPassword = () => {
  const [resetEmail, setResetEmail] = useState('');
  const [resetMsg, setResetMsg] = useState('');
  const [resetAlert, setResetAlert] = useState(false);
  const  [variant, setVariant] = useState('danger');
  const validateEmail = () => {
    const pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return pattern.test(resetEmail);
  };

  const resetSubmit = (event) => {
    event.preventDefault();
    setResetAlert(true);
    if(validateEmail){
      setResetMsg("Please provide valid email address!")
      return;
    }
    const params = { email: resetEmail }; // Single parameter
    UserService.postWithParams("/reset",{},params)
               .then((res) => { 
                  console.log(res.status);
                  if (res.status === 200) {
                    setVariant('success');
                  } 
                  setResetMsg(res.data);
               })
              .catch((e) => {
                console.error('Error:', e);
                setResetMsg('An error occurred while processing your request.');
              });
  };

  return (
    <div className="form">
    <Alert variant="info">
        Enter your email address in the space below and we will e-mail you the password reset instructions.
    </Alert>
      {resetAlert && <Alert variant={variant}>{resetMsg}</Alert>}
      <Form method="post" onSubmit={resetSubmit}>
        <FormGroup>
        <p className="my-2">Registered Email Address</p>
          <MDBInput
              placeholder='Email Address'
              id='emailId'
              type='emailId'
              size="lg"
              value={resetEmail}
              onChange={(e) => setResetEmail(e.target.value)}
            />
        </FormGroup>
        <div className="top-margin text-right">
          <Button type="submit" variant="primary">Reset</Button>
        </div>
      </Form>
    </div>
  );
  }
  
  export default ResetPassword;