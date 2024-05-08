import React from 'react';
import { Container, Row, Col } from 'react-bootstrap';

const FooterComponent = () => {
  const currentDate = new Date();
  const options = { day: 'numeric', month: 'long', year: 'numeric' };
  const formattedDate = currentDate.toLocaleDateString('en-GB', options);

  return (
    <Container fluid className="footerlog">
      <Row>
        <Col>
            <img src="images/EventCalender.svg" className="footer-img" alt="Eventx Logo" />
            <span style={{ fontFamily: 'Arial, Helvetica, sans-serif' }}>©</span> 2024 Eventx · All Rights Reserved ·
            <span className="badge" style={{ background: '#EEE', color: '#666' ,float: 'right' }}>v1.0.0 / {formattedDate}</span>
        </Col>
     </Row>
    </Container>
  );
};

export default FooterComponent