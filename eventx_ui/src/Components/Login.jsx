import React, { useState } from 'react';
import {
  MDBContainer,
  MDBRow,
  MDBCol,
  MDBInput
} from 'mdb-react-ui-kit';
import ResetPasswordPopup from './ResetPasswordPopup';
import ModalDialougePopUP from './ModalDialougePopUP';
import { Alert, Button, Row } from 'react-bootstrap';
import UserService from '../service/UserService';

const Login = () => {
  const [formData, setFormData] = useState({
    email: '',
    password: ''
  });
  const [message, setMessage] = useState('');
  const [variant, setVariant] = useState('danger');
  const [showModal, setShowModal] = useState(false);
  const [showAlert, setShowAlert] = useState(false);
  
  const handleChange = (e) => {
    setShowAlert(false);
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };
  const openModal = () => {
    setShowModal(true);
  };

  const closeModal = () => {
    setShowModal(false);
  };
  const validateForm = () => {
    if (!formData.email.trim()) {
      setMessage('Please enter email address');
      return false;
    }
    if (!/\S+@\S+\.\S+/.test(formData.email.trim())) {
      setMessage('Please enter valid email address');
      return false;
    }
    if (!formData.password.trim()) {
      setMessage('Please enter correct password');
      return false;
    }
    setMessage('');
    return true;
  };
  const handleLogin = (e) => {
    e.preventDefault();
    setVariant('danger');
    setShowAlert(true);
    if (!validateForm()) {
      return;
    }
    // If there are no errors, proceed with login
    
    console.log('Form Submitted:', formData);
        // Make POST request to your local URL
    UserService.post("/login",formData)
               .then((res) => { 
                  console.log(res.status);
                  if (res.status === 200) {
                    setVariant('success');
                    setMessage(res.data);
                  } else {
                    setMessage('Please enter correct username and password.');
                  }
               })
              .catch((e) => {
                console.error('Error:', e);
                setMessage('An error occurred while processing your request.');
              });
    };
  
  return (
    <MDBContainer>
      <MDBRow>
        <Row className='d-flex flex-row'>
          {showAlert && <Alert variant={variant}>{message}</Alert>}
        </Row>
        <MDBCol sm='6'>
          
        <form onSubmit={handleLogin}> 

        
          <div className='d-flex flex-column justify-content-center h-custom-2 w-75 pt-4'>
            <h3 className="fw-normal mb-3 ps-5 pb-3" style={{ letterSpacing: '1px' }}>Log in</h3>
            <MDBInput
              wrapperClass='mb-4 mx-5 w-100'
              placeholder='Email'
              id='email'
              name='email'
              type='text'
              size="lg"
              value={formData.email}
              onChange={handleChange}
            />
            <MDBInput
              wrapperClass='mb-4 mx-5 w-100'
              placeholder='Password'
              id='password'
              name='password'
              type='password'
              size="lg"
              value={formData.password}
              onChange={handleChange}
            />
            <Button
              className="mb-4 px-5 mx-5 w-100"
              color='info'
              size='lg'
              type='submit'
            >
              Login
            </Button>
            
            <p className="small mb-5 pb-lg-3 ms-5"><a className="text-muted" href="#!" onClick={openModal}>Forgot password?</a></p>
            <p className='ms-5'>Don't have an account? <a href="/register" className="link-info">Register here</a></p>
          </div>
        </form>
        </MDBCol>
        <MDBCol sm='6' className='d-none d-sm-block px-0'>
          <img
            src="images/EventManagement.jpg"
            alt="Login"
            className="w-100 h-100"
            style={{ objectPosition: 'left', objectFit:'contain' }}
          />
        </MDBCol>
      </MDBRow>
      <ModalDialougePopUP 
            show={showModal} 
            onClose={closeModal} 
            title="Reset Password" 
            bodyContent={<ResetPasswordPopup />} 
     />
    </MDBContainer>
  );
};

export default Login;
