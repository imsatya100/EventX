import React, { useState } from 'react';
import {
  MDBContainer,
  MDBRow,
  MDBCol,
  MDBIcon,
  MDBInput
} from 'mdb-react-ui-kit';
import ResetPassword from './ResetPassword';
import ModalDialougePopUP from './ModalDialougePopUP';
import { Alert, Button } from 'react-bootstrap';
import { useSelector } from 'react-redux';
import axios from 'axios';

const Login = () => {
  const [formData, setFormData] = useState({
    email: '',
    password: ''
  });
  const [error, setError] = useState('');
  const [variant, setVariant] = useState('danger');
  const [showModal, setShowModal] = useState(false);
  const [showAlert, setShowAlert] = useState(false);
  const message = useSelector(state => state.forwardMessage);
  
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
      setError('Email is required');
      return false;
    }
    if (!/\S+@\S+\.\S+/.test(formData.email.trim())) {
      setError('Invalid email format');
      return false;
    }
    if (!formData.password.trim()) {
      setError('Password is required');
      return false;
    }
    setError('');
    return true;
  };
  const handleLogin = (e) => {
    e.preventDefault();
    if (!validateForm()) {
      setShowAlert(true);
      return;
    }
    // If there are no errors, proceed with login
    console.log('Form Submitted:', formData);
        // Make POST request to your local URL
    axios.post('http://localhost:8040/api/v1/users/login', formData)
         .then((res) => { 
            console.log(res.status);
            if (res.status === 200) {
              setShowAlert(true)
              setVariant('success');
              setError(res.data);
              console.log(res);
            } else {
              setShowAlert(true)
              setError('Please enter correct username and password.');
            }
         })
         .catch((e) => {
            console.error('Error:', e);
            setShowAlert(true)
            setError('An error occurred while processing your request.');
         });
        
  };
  
  return (
    <MDBContainer fluid>
      <MDBRow>
        <MDBCol sm='6'>
          <div className='d-flex flex-row ps-5 pt-5'>
            <MDBIcon fas icon="crow fa-3x me-3" style={{ color: '#709085' }} />
          </div>
        <form onSubmit={handleLogin}> 

        {showAlert && <Alert variant={variant}>{error}</Alert>}
        {message && <Alert variant="info">{message}</Alert>}
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
          <ModalDialougePopUP 
            show={showModal} 
            onClose={closeModal} 
            title="Reset Password" 
            bodyContent={<ResetPassword />} 
          />
        </form>
        </MDBCol>
        <MDBCol sm='6' className='d-none d-sm-block px-0'>
          <img
            src="images/EventManagement.jpg"
            alt="Login"
            className="w-100 h-100"
            style={{ objectPosition: 'left', marginTop:'40px' }}
          />
        </MDBCol>
      </MDBRow>
    </MDBContainer>
  );
};

export default Login;
