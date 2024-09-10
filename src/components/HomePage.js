import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './HomePage.css';
import Header from './Header';
import { useNavigate } from 'react-router-dom';

const HomePage = ({ isLoggedIn, handleLogout }) => {
    const [categories, setCategories] = useState([]);
    const [selectedCategories, setSelectedCategories] = useState([]);
    const [products, setProducts] = useState([]);
    const [error, setError] = useState('');
    const [minPrice, setMinPrice] = useState('');
    const [maxPrice, setMaxPrice] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const fetchCategories = async () => {
            const token = localStorage.getItem('jwtToken');

            if (!token) {
                setError("No token found. Please log in.");
                return;
            }

            try {
                const response = await axios.get('/api/categories', {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                });
                setCategories(response.data);
            } catch (error) {
                console.error("Error fetching categories:", error);
                setError('Failed to load categories. Check console for details.');
            }
        };

        fetchCategories();
    }, []);

    useEffect(() => {
        const fetchProducts = async () => {
            const token = localStorage.getItem('jwtToken');
    
            if (!token) {
                setError("No token found. Please log in.");
                return;
            }
    
            try {
                const categoryId = selectedCategories.length > 0 ? selectedCategories[0].id : null;
                const queryParams = [];
    
                if (categoryId) queryParams.push(`categoryId=${categoryId}`);
    
                const queryString = queryParams.length > 0 ? `?${queryParams.join('&')}` : '';
    
                const response = await axios.get(`/api/products${queryString}`, {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                });
    
                let filteredProducts = response.data;
    
                if (minPrice) {
                    filteredProducts = filteredProducts.filter(product => product.price >= minPrice);
                }
    
                if (maxPrice) {
                    filteredProducts = filteredProducts.filter(product => product.price <= maxPrice);
                }
    
                setProducts(filteredProducts);
            } catch (error) {
                console.error("Error fetching products:", error);
                setError('Failed to load products. Check console for details.');
            }
        };
    
        if (selectedCategories.length > 0 || minPrice || maxPrice) {
            fetchProducts();
        } else {
            setProducts([]);
        }
    }, [selectedCategories, minPrice, maxPrice]);
    

    const handleCategoryChange = (category) => {
        setSelectedCategories([category]);
    };

    const handleMinPriceChange = (e) => {
        setMinPrice(e.target.value);
    };

    const handleMaxPriceChange = (e) => {
        setMaxPrice(e.target.value);
    };

   
    const addToCart = (product) => {
        const existingCart = JSON.parse(localStorage.getItem('cart')) || [];
    
        const productInCart = existingCart.find(item => item.uniqueId === product.uniqueId);
    
        if (productInCart) {

            productInCart.quantity += 1;
        } else {
            existingCart.push({ ...product, quantity: 1 });
        }
    
        localStorage.setItem('cart', JSON.stringify(existingCart));
    
        alert(`${product.name} added to cart`);
    };
    
    


    return (
        <div>
            <Header isLoggedIn={isLoggedIn} handleLogout={handleLogout} />

            <div className="content-container">
                <div className="sidebar">
                    <h2>Categories</h2>
                    <div className="category-filters">
                        {categories.map(category => (
                            <label key={category.id}>
                                <input
                                    type="checkbox"
                                    value={category.id}
                                    onChange={() => handleCategoryChange(category)}
                                    checked={selectedCategories.some(c => c.id === category.id)}
                                />
                                {category.name}
                            </label>
                        ))}
                    </div>

                    <div className="price-filters">
                        <h3>Filter by Price</h3>
                        <label>
                            Min Price:
                            <input
                                type="number"
                                value={minPrice}
                                onChange={handleMinPriceChange}
                                placeholder="Min"
                            />
                        </label>
                        <label>
                            Max Price:
                            <input
                                type="number"
                                value={maxPrice}
                                onChange={handleMaxPriceChange}
                                placeholder="Max"
                            />
                        </label>
                    </div>
                </div>

                <div className="products">
                    <h2>Products</h2>
                    <ul>
                        {products.length > 0 ? (
                           products.map(product => (
                            <li key={`${product.id}-${product.category}-${product.name}`}>
                                <h3>{product.name}</h3>
                                <p>Price: ${product.price}</p>
                                <img src={product.photoUrl} alt={product.name} width="150" />
                                <button onClick={() => addToCart({ 
                                    ...product, 
                                    uniqueId: `${product.id}-${product.category}-${product.name}` 
                                })}>
                                    Add to Cart
                                </button>
                            </li>
                        ))
                        
                        
                        ) : (
                            <p>No products found</p>
                        )}
                    </ul>
                </div>
            </div>
        </div>
    );
};

export default HomePage;
