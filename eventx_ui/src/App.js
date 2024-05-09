import React from 'react';
import './App.css';
import {BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import UserRegistration from './Components/UserRegistration';
import FooterComponent from './Components/FooterComponent';
import Navbar from './Components/NavBar';
import Login from './Components/Login';
import HomePage from './Components/HomePage';
import ResetPassword from './Components/ResetPassword';
import ChangePassword from './Components/ChangePassword';
function App() {
    return (
    <>
      <Router>
      <Navbar />
      <div id="content">
          <Routes>
             <Route path="/" element={<HomePage/>} />
             <Route path="/login" element={<Login/>} />
             <Route path="/register" element={<UserRegistration/>} />
             <Route path="/resetPassword" element={<ResetPassword/>} />
             <Route path="/changePassword" element={<ChangePassword/>} />
          </Routes>
      </div>
      <FooterComponent />
     </Router>
    </>
    
  );
}

export default App;
