import React from 'react';
import './App.css';
import {BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import UserRegistration from './Components/UserRegistration';
import FooterComponent from './Components/FooterComponent';
import Navbar from './Components/NavBar';
import Login from './Components/Login';
import { Container } from 'react-bootstrap';
import HomePage from './Components/HomePage';
import ResetPassword from './Components/ResetPassword';
function App() {
    return (
    <div>
      <Router>
      <Navbar />
      <Container>
          <Routes>
             <Route path="/" element={<HomePage/>} />
             <Route path="/login" element={<Login/>} />
             <Route path="/register" element={<UserRegistration/>} />
             <Route path="/resetPassword" element={<ResetPassword/>} />
          </Routes>
      </Container>
      <FooterComponent />
     </Router>
    </div>
    
  );
}

export default App;
