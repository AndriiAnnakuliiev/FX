import React, { useState, useEffect } from 'react';
import Header from './Header'; 
import './ShoppingCart.css';
import axios from 'axios'; 

const ShoppingCart = ({ isLoggedIn, handleLogout }) => {
    const [cart, setCart] = useState([]);

    useEffect(() => {
        const storedCart = JSON.parse(localStorage.getItem('cart'));
        if (storedCart) {
            setCart(storedCart);
        }
    }, []);

    const saveCartToLocalStorage = (updatedCart) => {
        localStorage.setItem('cart', JSON.stringify(updatedCart));
    };

    const removeFromCart = (uniqueId) => {
        const updatedCart = cart.filter(item => item.uniqueId !== uniqueId);
        setCart(updatedCart);
        saveCartToLocalStorage(updatedCart);
    };
    

    const calculateTotal = () => {
        return cart.reduce((total, item) => total + item.price * item.quantity, 0).toFixed(2);
    };

    const handleCheckout = async () => {
        try {
            const token = localStorage.getItem('jwtToken'); 
            const total = calculateTotal();
            console.log('Checkout request data:', { cart, total });
    
            const response = await axios.post('/api/checkout', {
                cart,
                total
            }, {
                headers: {
                    'Authorization': `Bearer ${token}`  
                }
            });
    
            console.log('Checkout response:', response.data);
            alert('Checkout successful! An email confirmation will be sent to you.');
        } catch (error) {
            console.error('Error during checkout:', error);
            alert('Failed to complete checkout. Please try again.');
        }
    };
    
    

    return (
        <div>
            <Header isLoggedIn={isLoggedIn} handleLogout={handleLogout} /> 
            <div className="shopping-cart">
                <h1>Shopping Cart</h1>
                {cart.length === 0 ? (
                    <p>Your cart is empty</p>
                ) : (
                    <div>
                        {cart.map((item) => (
                            <div className="cart-item" key={item.uniqueId}>
                            <p>{item.name}</p>
                            <p>Quantity: {item.quantity}</p>
                            <p>Price: ${item.price}</p>
                            <button onClick={() => removeFromCart(item.uniqueId)}>Remove</button>
                        </div>
                        ))}

                        <div className="cart-total">
                            <h2>Total: ${calculateTotal()}</h2>
                        </div>
                        <button className="checkout-button" onClick={handleCheckout}>Proceed to Checkout</button>
                    </div>
                )}
            </div>
        </div>
    );
};

export default ShoppingCart;
