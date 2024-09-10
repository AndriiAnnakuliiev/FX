import React, { useState, useEffect } from 'react';
import Header from './Header';
import axios from 'axios';
import './UserProfile.css';

const UserProfile = ({ isLoggedIn, handleLogout }) => {
    const [error, setError] = useState('');
    const [productData, setProductData] = useState({});
    const [fields, setFields] = useState([]);
    const [category, setCategory] = useState('ssds');

    const categories = [
        { label: 'SSDs', value: 'ssds' },
        { label: 'Graphics Cards', value: 'graphicscards' },
        { label: 'Motherboards', value: 'motherboards' },
        { label: 'Power Supplies', value: 'powersupplies' },
        { label: 'Processors', value: 'processors' },
        { label: 'RAMs', value: 'rams' },
    ];

    const categoryToPathMap = {
        ssds: '/api/tables/ssds',
        graphicscards: '/api/tables/graphicscards',
        motherboards: '/api/tables/motherboards',
        powersupplies: '/api/tables/powersupplies',
        processors: '/api/tables/processors',
        rams: '/api/tables/rams',
    };
    

    const categoryToFieldsPathMap = {
        ssds: '/api/tables/ssds/fields',
        graphicscards: '/api/tables/graphicscards/fields',
        motherboards: '/api/tables/motherboards/fields',
        powersupplies: '/api/tables/powersupplies/fields',
        processors: '/api/tables/processors/fields',
        rams: '/api/tables/rams/fields',
    };

    useEffect(() => {
        const fetchFields = async () => {
            const token = localStorage.getItem('jwtToken');
            if (!token) {
                setError("No token found. Please log in.");
                console.log('No token found');
                return;
            }

            try { 
                console.log('JWT Token:', token);
                console.log('Fetching fields for:', categoryToFieldsPathMap[category]);
                const response = await axios.get(categoryToFieldsPathMap[category], {
                    headers: { 'Authorization': `Bearer ${token}` }
                });
                setFields(response.data);
            } catch (error) {
                console.error("Error fetching fields:", error.response?.data || error.message);
                setError('1 - Failed to load fields. Please check console for details.');
            }
        };

        fetchFields();
    }, [category]);

    const handleInputChange = (e) => {
        const { name, value, type, checked } = e.target;
        setProductData(prevState => ({
            ...prevState,
            [name]: type === 'checkbox' ? checked : value,
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const token = localStorage.getItem('jwtToken');
        if (!token) {
            setError("No token found. Please log in.");
            return;
        }

        try {
            const response = await axios.post(categoryToPathMap[category], productData, {
                headers: { 'Authorization': `Bearer ${token}` }
            });
            console.log('Product added successfully:', response.data);
            alert(`Product added to ${category} table!`);
        } catch (error) {
            console.error("Error adding product:", error.response?.data || error.message);
            setError('2 - Failed to add product. Please check console for details.');
        }
    };

    return (
        <div>
            <Header isLoggedIn={isLoggedIn} handleLogout={handleLogout} />
            <div className="user-profile">
                <div className="product-form">
                    <h2>Add New Product</h2>
                    <label>Select Table:</label>
                    <select value={category} onChange={(e) => setCategory(e.target.value)}>
                        {categories.map(cat => (
                            <option key={cat.value} value={cat.value}>
                                {cat.label}
                            </option>
                        ))}
                    </select>

                    <form onSubmit={handleSubmit}>
                        {fields.map(field => (
                            <div key={field.fieldName} className="form-group">
                                <label>{field.fieldName}</label>
                                <input
                                    type={field.fieldType === 'checkbox' ? 'checkbox' : field.fieldType}
                                    name={field.fieldName}
                                    onChange={handleInputChange}
                                    required={field.isRequired}
                                />
                            </div>
                        ))}
                        <button type="submit">Add Product</button>
                    </form>

                    {error && <div className="error-message">{error}</div>}
                </div>
            </div>
        </div>
    );
};

export default UserProfile;
