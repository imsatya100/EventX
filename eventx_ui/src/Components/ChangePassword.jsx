import React, { useState } from 'react';
import { MDBContainer, MDBRow, MDBCol, MDBInput } from 'mdb-react-ui-kit';
import { Alert, Button, Row } from 'react-bootstrap';

const ChangePassword = () => {
  const [formData, setFormData] = useState({
    currentPassword: '',
    newPassword: '',
    confirmPassword: ''
  });
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
      setMessage('Please enter current password ');
      return false;
    }
    if (!formData.newPassword) {
      setMessage('Please enter new password ');
      return false;
    } else if (formData.newPassword.length < 8) {
      setMessage('Entered new password must be at least 8 characters long');
      return false;
    }
    if (!formData.confirmPassword) {
      setMessage('Please re-enter new password');
      return false;
    } else if (formData.password !== formData.confirmPassword) {
      setMessage('New password and confirm new password do not match');
      return false;
    }
    setMessage('');
    return true;
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    setShowAlert(true);
    setMessage('');
    setVariant('danger');
    if (!validateForm()) {
      return;
    }
    // Your form submission logic here
    console.log(formData);
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
              <h3 className="fw-normal mb-3 ps-5 pb-3" style={{ letterSpacing: '1px' }}>Change Password</h3>
              <MDBInput
                wrapperClass='mb-4 mx-5 w-100'
                placeholder='Current Password'
                id='password'
                name='password'
                type='password'
                size="lg"
                value={formData.password}
                onChange={handleChange}
              />
              <MDBInput
                wrapperClass='mb-4 mx-5 w-100'
                placeholder='New Password'
                id='newPassword'
                name='newPassword'
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
              Change Password
            </Button>
            </div>
          </form>
        </MDBCol>
      </MDBRow>
    </MDBContainer>
  );
};

export default ChangePassword;
