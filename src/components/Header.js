import React from 'react';
import { Link, useNavigate } from 'react-router-dom'; 
import './Header.css';

const Header = ({ isLoggedIn, handleLogout }) => {
    const navigate = useNavigate();

    const handleLogoutClick = () => {
        handleLogout();
        navigate("/login");  
    };

    return (
        <header className="site-header">
            <div className="logo">
                <Link to="/">Computer Store</Link> 
            </div>
            <nav>
                
            </nav>
            <div className="right-menu">
                <Link to="/cart">Cart</Link>
                <Link to="/profile">Profile</Link>
                <Link to="/about">About Us</Link>
                {isLoggedIn ? (
                    <button onClick={handleLogoutClick} className="logout-button">Logout</button>
                ) : (
                    <Link to="/login">Login</Link>
                )}
            </div>
        </header>
    );
};

export default Header;
