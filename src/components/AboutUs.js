import React from 'react';
import './AboutUs.css';
import Header from './Header';

const AboutUs = ({ isLoggedIn, handleLogout }) => {
    return (
        <div>
           
            <Header isLoggedIn={isLoggedIn} handleLogout={handleLogout} />
            <div className="about-us">
                <h1>About Us</h1>
                <p>Welcome to Computer Store, your trusted source for high-quality computer components. Whether you are building a new gaming rig, upgrading your office workstation, or simply looking for the latest tech gadgets, we have everything you need.</p>
                <h2>Our Story</h2>
                <p>Founded in 2010, Computer Store started as a small local business with a mission to provide quality computer hardware to tech enthusiasts and professionals. Over the years, we have grown into a recognized brand with a broad product range, serving customers from all over the country.</p>
                <h2>What We Offer</h2>
                <p>We specialize in:</p>
                <ul>
                    <li>State-of-the-art processors from top brands like Intel, AMD, and more</li>
                    <li>High-performance graphic cards for gaming, 3D rendering, and video editing</li>
                    <li>Motherboards to fit any system configuration</li>
                    <li>SSD and RAM to improve your system’s speed and performance</li>
                    <li>Reliable power supplies for stable and efficient energy distribution</li>
                    <li>And much more – we cover every component needed for your perfect setup</li>
                </ul>
                <h2>Why Choose Us?</h2>
                <p>At Computer Store, we pride ourselves on delivering top-tier products with an unmatched customer experience. Here’s why our customers keep coming back:</p>
                <ul>
                    <li><b>Expert Advice:</b> Our team of specialists is always ready to help you make the right choice based on your needs and budget.</li>
                    <li><b>Competitive Pricing:</b> We offer some of the best deals on the market without compromising on quality.</li>
                    <li><b>Fast and Secure Delivery:</b> We understand the importance of getting your tech as quickly as possible, and that’s why we provide fast and reliable shipping options.</li>
                    <li><b>Customer Satisfaction:</b> Our priority is ensuring that every customer is fully satisfied with their purchase and support.</li>
                </ul>
                <h2>Our Vision</h2>
                <p>Our vision is to continue growing as a premier supplier of cutting-edge computer hardware, expanding our reach and product selection to meet the ever-evolving needs of the tech community. We strive to be a one-stop shop for both everyday consumers and tech enthusiasts alike.</p>
                <h2>Contact Us</h2>
                <p>If you have any questions, inquiries, or need expert advice, feel free to reach out:</p>
                <ul>
                    <li><b>Email:</b> info@computerstore.com</li>
                    <li><b>Phone:</b> +1-800-123-4567</li>
                    <li><b>Address:</b> 123 Tech Street, Silicon Valley, CA 94043</li>
                </ul>
                <h2>Follow Us</h2>
                <p>Stay connected and follow us on our social media channels to get the latest updates on new products, offers, and technology trends.</p>
            </div>
        </div>
    );
};

export default AboutUs;
