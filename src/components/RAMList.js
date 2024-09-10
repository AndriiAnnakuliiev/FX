import React, { useEffect, useState } from 'react';
import axios from 'axios';

const RAMList = () => {
  const [rams, setRams] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/api/rams')
      .then(response => {
        setRams(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the RAM data!', error);
      });
  }, []);

  return (
    <div>
      <h1>List of RAMs</h1>
      <ul>
        {rams.map(ram => (
          <li key={ram.id}>
            {ram.name} - {ram.capacity}GB - ${ram.price}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default RAMList;
