import { useState } from 'react';
import { useEffect } from 'react';
import axios from '../api/axios';

const useFetch = (url) => {
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const getData = async () => {
            try {
                const response = await axios.get(url);
                setData(response.data);
                setError(null);
            } catch (err) {
                setError(err.message);
                setData(null);
            } finally {
                setLoading(false);
            }
        };
        getData();  
        // eslint-disable-next-line react-hooks/exhaustive-deps
    },[url]);

    return { data, loading, error }
}

export default useFetch;