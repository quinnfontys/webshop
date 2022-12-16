import { over } from 'stompjs';
import SockJS from 'sockjs-client';
import { useEffect, useState } from 'react';

var stompClient = null;
const Chatroom = () => {
    const [publicChats, setPublicChats] = useState([])
    const [userData, setUserData] = useState({
        username: "",
        connected: false,
        message: ""
    })

    useEffect(() => {
        console.log(userData);
      }, [userData]);

    const handleValue = (event) => {
        const {value, name} = event.target;
        setUserData({...userData, [name] : value})
    }

    const registerUser = () => {
        let Sock = new SockJS("http://localhost:8090/ws");
        stompClient = over(Sock);
        stompClient.connect({}, onConnected, onError);
    }

    const onConnected = () => {
        setUserData({...userData, "connected" : true});
        stompClient.subscribe('/chatroom/public', onMessageReceived);
        userJoin();
    }

    const userJoin = () => {
        let chatMessage = {
            senderName: userData.username,
            status: "JOIN",
        };
        stompClient.send('/app/message', {}, JSON.stringify(chatMessage))
    }

    const onMessageReceived = (payload) => {
        let payloadData = JSON.parse(payload.body);
        switch(payloadData.status) {
            case "JOIN" :
                break;
            case "MESSAGE" :
                publicChats.push(payloadData);
                setPublicChats([...publicChats]);
                break;
            default:
                break;

        }
    }

    const onError = (err) => {
        console.log(err)
    }

    

    const sendPublicMessage = () => {
        if (stompClient) {
            let chatMessage = {
                senderName: userData.username, 
                message: userData.message,
                status: 'MESSAGE'
            };
            stompClient.send('/app/message', {}, JSON.stringify(chatMessage));
            setUserData({...userData, "message" : ""})
        }
    }

    return (  
        <div className="container">
            {userData.connected?
                <div className="chat-box">
                    <div className="chat-content">
                        {publicChats.map((chat, index) => (
                            <div className="message" key={index}>
                                <div className="message-name">{chat.senderName}</div>
                                <div className="message-data">{chat.message}</div>
                            </div>
                        ))}
                    </div>
                    <div className="send-message">
                        <input type="text" className="input-message" name="message" placeholder='Enter message' value={userData.message} onChange={handleValue}></input>
                        <button type="button" className="send-button" onClick={sendPublicMessage}>Send</button>
                    </div>
                </div>
                :
                <div className="register">
                    <input
                    id="user-name"
                    name="username"
                    placeholder='Enter the username'
                    value={userData.username}
                    onChange={handleValue}
                    />
                    <button type='button' onClick={registerUser}>
                        connect
                    </button>
                </div> 
            }
        </div>
    );
}
 
export default Chatroom;