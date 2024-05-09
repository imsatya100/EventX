// apiService.js

import axios from 'axios';

const BASE_URL = 'http://localhost:8040/api/v1/users';

const UserService = {
  async get(endpoint) {
    try {
      const response = await axios.get(`${BASE_URL}${endpoint}`);
      return response;
    } catch (error) {
      console.error('Error fetching data:', error);
      throw error;
    }
  },

  async post(endpoint, data) {
    try {
      const response = await axios.post(`${BASE_URL}${endpoint}`, data, {
        headers: {
          'Content-Type': 'application/json',
        },
      });
      return response;
    } catch (error) {
      console.error('Error posting data:', error);
      throw error;
    }
  },
  async postWithParams(endpoint, data, params) {
    try {
      const queryParams = new URLSearchParams(params).toString();
      const response = await fetch(`${BASE_URL}${endpoint}?${queryParams}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
      });
      return await response;
    } catch (error) {
      console.error('Error posting data with params:', error);
      throw error;
    }
  },
};

export default UserService;
