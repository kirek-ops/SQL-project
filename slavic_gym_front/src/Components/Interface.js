import React, { useState, useEffect } from 'react';
import { useNavigate, useLocation } from "react-router-dom";
import axios from 'axios';

const Interface = () => {
    const location = useLocation();
    const id = location.state.id;
    const email = location.state.email;

    const navigate = useNavigate();

    const [gyms, setGyms] = useState([]);
    const [selectedGym, setSelectedGym] = useState('');
    const [userLocation, setUserLocation] = useState(null);

    useEffect(() => {
        // Get user geolocation
        navigator.geolocation.getCurrentPosition(
            position => {
                setUserLocation({
                    latitude: position.coords.latitude,
                    longitude: position.coords.longitude
                });
            },
            error => {
                console.error('Error obtaining geolocation:', error);
            }
        );
    }, []);

    useEffect(() => {
        axios.get('http://localhost:8080/gyms/getopengyms')
            .then(response => {
                if (Array.isArray(response.data)) {
                    setGyms(response.data);
                } else {
                    console.error('API response is not an array:', response.data);
                }
            })
            .catch(error => {
                console.error('There was an error fetching the gym data!', error);
            });
    }, []);

    const handleGymChange = (event) => {
        setSelectedGym(event.target.value);
    };

    const handleQRButtonClick = () => {
        if (selectedGym) {
            navigate('/myqr', { state: { id: id, email: email, gym: selectedGym } });
        } else {
            alert("Please select a gym before proceeding.");
        }
    };

    const handleMembershipShopClick = () => {
        navigate('/membershipshop', { state: { id: id, email: email } });
    }

    return (
        <div style={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            justifyContent: 'center',
            height: '100vh'
        }}>
            <h1 style={{marginBottom: '30px', color: '#007BFF'}}>Welcome to the Gym App</h1>

            <div style={{marginBottom: '20px'}}>
                <label htmlFor="gym-select" style={{marginRight: '10px', fontSize: '18px'}}>Choose a gym:</label>
                <select id="gym-select" value={selectedGym} onChange={handleGymChange}
                        style={{padding: '10px', fontSize: '16px'}}>
                    <option value="">Select a gym</option>
                    {Array.isArray(gyms) && gyms.map((gym, index) => (
                        <option key={index} value={gym.id_gym}>{gym.name} - {gym.distance ? `${gym.distance.toFixed(2)} km` : 'Unknown'}</option>
                    ))}
                </select>
            </div>

            <button style={{
                padding: '10px 20px',
                fontSize: '16px',
                backgroundColor: '#007BFF',
                color: 'white',
                border: 'none',
                borderRadius: '5px',
                cursor: 'pointer'
            }} onClick={handleQRButtonClick}>
                Get My QR Code
            </button>
            <button style={{
                padding: '10px 20px',
                fontSize: '16px',
                backgroundColor: '#007BFF',
                color: 'white',
                border: 'none',
                borderRadius: '5px',
                cursor: 'pointer',
                marginTop: '20px'
            }} onClick={handleVisitsButton}>
                See my visits
            </button>

            <button style={{
                marginTop: '20px',
                padding: '10px 20px',
                fontSize: '16px',
                backgroundColor: '#007BFF',
                color: 'white',
                border: 'none',
                borderRadius: '5px',
                cursor: 'pointer'
            }} onClick={handleMembershipShopClick}>
                My memberships
            </button>
        </div>
    );
}

export default Interface;
