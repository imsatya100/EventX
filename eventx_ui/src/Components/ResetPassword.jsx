import React, { useState } from 'react';
import { MDBContainer, MDBRow, MDBCol, MDBInput } from 'mdb-react-ui-kit';
import { Alert, Button, Row } from 'react-bootstrap';
import { useParams } from 'react-router-dom';

const ChangePassword = () => {
  const [formData, setFormData] = useState({
    newPassword: '',
    confirmPassword: ''
  });
  const { email } = useParams();
  const [message, setMessage] = useState('');
  const [variant, setVariant] = useState('danger');
  const [showAlert, setShowAlert] = useState(false);

  const handleChange = (e) => {
    setShowAlert(false);
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };
  const validateForm = () => {
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
    console.log('Email:', email);
    console.log('New Password:', formData.newPassword);
    setVariant('success');
    setMessage('Password reset successful.');
  };

  return (
    <MDBContainer>
      <MDBRow>
        <Row className='d-flex flex-row'>
          {showAlert && <Alert variant={variant}>{message}</Alert>}
        </Row>
        <MDBCol sm='6'>
          <form onSubmit={handleSubmit}>
            <div className='d-flex flex-column justify-content-center h-custom-2 w-75 pt-4'>
              <h3 className="fw-normal mb-3 ps-5 pb-3" style={{ letterSpacing: '1px' }}>Reset Password</h3>
              <MDBInput
                wrapperClass='mb-4 mx-5 w-100'
                placeholder='Email Address'
                id='email'
                name='email'
                type='email'
                size="lg"
                value={formData.email}
                disabled
              />
              <MDBInput
                wrapperClass='mb-4 mx-5 w-100'
                placeholder='New Password'
                id='password'
                name='password'
                type='password'
                size="lg"
                value={formData.newPassword}
                onChange={handleChange}
              />
              <MDBInput
                wrapperClass='mb-4 mx-5 w-100'
                placeholder='Confirm New Password'
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
              Reset Password
            </Button>
            </div>
          </form>
        </MDBCol>
      </MDBRow>
    </MDBContainer>
  );
};

export default ChangePassword;
