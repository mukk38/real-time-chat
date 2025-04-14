import React, { useEffect, useState } from "react";
import SockJS from "sockjs-client";
import Stomp from "stompjs";

const ChatApp = () => {
  const [stompClient, setStompClient] = useState(null);
  const [messages, setMessages] = useState([]);
  const [message, setMessage] = useState("");
  const [username, setUsername] = useState("testUser"); // İsterseniz dinamik alabilirsiniz
  const [groupName, setGroupName] = useState("myGroup");

  useEffect(() => {
    const socket = new SockJS("http://localhost:8080/chat"); // WebSocket endpoint
    const client = Stomp.over(socket);
    setStompClient(client);

    // JWT token'ı header'a ekliyoruz
    const token = localStorage.getItem("jwtToken");

    client.connect(
      { Authorization: "Bearer " + token },
      (frame) => {
        console.log("Connected: " + frame);

        // Grup mesajlarını almak
        client.subscribe(`/topic/group/${groupName}`, (messageOutput) => {
          const newMessage = JSON.parse(messageOutput.body);
          setMessages((prevMessages) => [...prevMessages, newMessage]);
        });
      },
      (error) => {
        console.error("STOMP connection failed:", error);
      }
    );

    return () => {
      if (client.connected) {
        client.disconnect();
      }
    };
  }, [groupName]);

  const sendMessage = () => {
    if (stompClient && message.trim() !== "") {
      const messageObject = {
        sender: username,
        content: message,
        timestamp: new Date().toISOString(),
      };
      stompClient.send(`/app/group/${groupName}`, {}, JSON.stringify(messageObject));
      setMessage(""); // Mesaj gönderildikten sonra inputu temizle
    }
  };

  return (
    <div>
      <h2>Real-Time Chat</h2>
      <div>
        <ul id="messages">
          {messages.map((msg, index) => (
            <li key={index}>
              <strong>{msg.sender}</strong>: {msg.content}
            </li>
          ))}
        </ul>
        <input
          type="text"
          value={message}
          onChange={(e) => setMessage(e.target.value)}
          placeholder="Type a message"
        />
        <button onClick={sendMessage}>Send</button>
      </div>
    </div>
  );
};

export default ChatApp;
