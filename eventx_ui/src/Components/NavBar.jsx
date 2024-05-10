import React from 'react';
import { Navbar, Nav} from 'react-bootstrap';

const NavBar = () =>{
  return (
    <Navbar bg="light" expand="lg" style={{ borderBottom: '1px solid #000' , padding: '15px'}}>
      <Navbar.Brand href="/">
        GreetsnMeets 
      </Navbar.Brand>
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="ml-auto">
          <Nav.Link href="/">Home</Nav.Link>
          <Nav.Link href="/login">Login</Nav.Link>
          <Nav.Link href="/register">Register</Nav.Link>
          <Nav.Link href="/changePassword">Change Password</Nav.Link>
          <Nav.Link href="/">About</Nav.Link>
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
};

export default NavBar;
