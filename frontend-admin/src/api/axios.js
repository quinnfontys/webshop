import axios from 'axios';

const BASE_URL = 'http://localhost:8090'

export default axios.create({
    baseURL: BASE_URL
});

//attach interceptors to axios instance
//interceptors work with JWT tokens to refresh the token when innitial request is denied due to expired token
export const axiosPrivate = axios.create({
    baseURL: BASE_URL,
    headers: { 'Content-Type': 'application/JSON' },
    withCredentials: true
});