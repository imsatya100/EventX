import React, { useState } from 'react';
import { Alert, Form, FormGroup, Button } from 'react-bootstrap';
import { MDBInput } from 'mdb-react-ui-kit';
const ResetPassword = ({ show, onClose }) => {
    const [email, setEmail] = useState('');
  const [emailError, setEmailError] = useState('');
  const [showAlert, setShowAlert] = useState(false);

  const handleSubmit = (event) => {
    event.preventDefault();
    if (validateEmail(email)) {
      // Your form submission logic here
      setShowAlert(false); // Hide any previous alert
      console.log('Form submitted with email:', email);
    } else {
      setEmailError('Please enter a valid email address.');
      setShowAlert(true);
    }
  };

  // Regular expression for email validation
  const validateEmail = (email) => {
    const pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return pattern.test(email);
  };

  return (
    <div className="form">
    <Alert variant="info">
        Enter your email address in the space below and we will mail you the password reset instructions.
    </Alert>
      {showAlert && <Alert variant="danger">{emailError}</Alert>}
      <Form method="post" onSubmit={handleSubmit}>
        <FormGroup>
        <p className="my-2">Registered Email Address</p>
          <MDBInput
              placeholder='Email Address'
              id='emailId'
              type='emailId'
              size="lg"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          <label htmlFor="emailId" generated="true" id="emailIdError" className="error text-left"></label>
        </FormGroup>
        <div className="top-margin text-right">
          <Button type="submit" variant="primary">Reset</Button>
        </div>
      </Form>
    </div>
  );
  }
  
  export default ResetPassword;