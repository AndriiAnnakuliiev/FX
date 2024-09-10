import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import HomePage from './components/HomePage';
import UserProfile from './components/UserProfile';
import ShoppingCart from './components/ShoppingCart';
import AboutUs from './components/AboutUs';
import LoginPage from './components/LoginPage';
import Products from './components/Products';
import RegisterPage from './components/RegisterPage';
import axios from 'axios';

function App() {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const token = localStorage.getItem('jwtToken');
        if (token) {
            axios.post('/api/auth/validate-token', {}, {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            })
            .then(response => {
                setIsLoggedIn(true);
            })
            .catch(err => {
                setIsLoggedIn(false);
                localStorage.removeItem('jwtToken');
            })
            .finally(() => {
                setLoading(false);
            });
        } else {
            setLoading(false);
        }
    }, []);

    const handleLogout = () => {
        localStorage.removeItem('jwtToken');
        setIsLoggedIn(false);
    };

    console.log("setIsLoggedIn function from App.js:", setIsLoggedIn);

    if (loading) {
        return <div>Loading...</div>;
    }

    return (
        <Router>
            <Routes>
                <Route path="/" element={isLoggedIn ? <HomePage isLoggedIn={isLoggedIn} handleLogout={handleLogout} /> : <Navigate to="/login" />} />
                <Route path="/login" element={<LoginPage setIsLoggedIn={setIsLoggedIn} />} />
                <Route path="/register" element={<RegisterPage />} />
                <Route path="/cart" element={isLoggedIn ? <ShoppingCart isLoggedIn={isLoggedIn} handleLogout={handleLogout} /> : <Navigate to="/login" />} />
                <Route path="/products" element={isLoggedIn ? <Products /> : <Navigate to="/login" />} />
                <Route path="/about" element={<AboutUs isLoggedIn={isLoggedIn} handleLogout={handleLogout} />} />
                <Route path="/profile" element={isLoggedIn ? <UserProfile isLoggedIn={isLoggedIn} handleLogout={handleLogout} /> : <Navigate to="/login" />} />
            </Routes>
        </Router>
    );
}

export default App;
