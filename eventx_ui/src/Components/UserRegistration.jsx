import React, { useState } from 'react';
import { MDBContainer, MDBRow, MDBCol, MDBInput } from 'mdb-react-ui-kit';
import { Alert, Button, Row } from 'react-bootstrap';
import UserService from '../service/UserService';
const UserRegistration = () => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    password: '',
    confirmPassword:'',
  });
  const  [message, setMessage] = useState('');
  const  [variant, setVariant] = useState('danger');
  const [showAlert, setShowAlert] = useState(false);
  const handleChange = (e) => {
    setShowAlert(false);
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };
  const validateForm = () => {
    if (!formData.name) {
      setMessage('Please enter full name');
      return false;
    }
    if (!formData.email) {
      setMessage('Please enter email address');
      return false;
    } else if (!/\S+@\S+\.\S+/.test(formData.email)) {
      setMessage('Please enter valid email address ');
      return false;
    }
    if (!formData.password) {
      setMessage('Please enter password ');
      return false;
    } else if (formData.password.length < 8) {
      setMessage('Entered password must be at least 8 characters long');
      return false;
    }
    if (!formData.confirmPassword) {
      setMessage('Please re-enter your password');
      return false;
    } else if (formData.password !== formData.confirmPassword) {
      setMessage('Password and confirm password do not match');
      return false;
    }
    setMessage('');
    return true;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setVariant('danger');
    setShowAlert(true);
    // Validate form data here (e.g., check if fields are not empty, valid email format, etc.)
    if (!validateForm()) {
      return;
    }
    console.log('Form Submitted:', formData);
    UserService.post("",formData)
               .then((res) => { 
                  console.log(res.status);
                  if (res.status === 200) {
                    setVariant('success');
                  } 
                  setMessage(res.data);
                })
              .catch((e) => {
                console.error('Error:', e);
                setMessage('An error occurred while processing your request.');
              });
    }; 
  ;

   return (
    <MDBContainer>
    <MDBRow>
      <Row className='d-flex flex-row'>
        {showAlert && <Alert variant={variant}>{message}</Alert>}
      </Row>
      <MDBCol sm='6'>
     <form onSubmit={handleSubmit}> 
        <div className='d-flex flex-column justify-content-center h-custom-2 w-75 pt-4'>
          <h3 className="fw-normal mb-3 ps-5 pb-3" style={{ letterSpacing: '1px' }}>Register</h3>
          <MDBInput
            wrapperClass='mb-4 mx-5 w-100'
            placeholder='Full Name'
            id='name'
            name='name'
            type='text'
            size="lg"
            value={formData.name}
            onChange={handleChange}
          />
          <MDBInput
            wrapperClass='mb-4 mx-5 w-100'
            placeholder='Email address'
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
          <MDBInput
            wrapperClass='mb-4 mx-5 w-100'
            placeholder='Confirm Password'
            id='confirmPassword'
            name='confirmPassword'
            type='password'
            size="lg"
            value={formData.confirmPassword}
            onChange={handleChange}
          />
          <Button
            className="mb-4 px-5 mx-5 w-100"
            color='info'
            size='lg'
            type='submit'
          >
            Register
          </Button>
          <p className='ms-5'>Already have an account? <a href="/login" className="link-info">Login here</a></p>
        </div>
      </form>          
      </MDBCol>
      <MDBCol sm='6' className='d-none d-sm-block px-0'>
        <img
          src="images/EventManagement.jpg"
          alt="Register"
          className="w-100 h-100"
          style={{ objectPosition: 'left', objectFit:'contain' }}
        />
      </MDBCol>
    </MDBRow>
  </MDBContainer>
  );
};

export default UserRegistration;
