# A-application-chat-by-java


# Architecture:
Server: One user acts as the server, which waits for connections from the client.
Client: The other user connects to the server and can start sending and receiving messages.
Both the client and server will run in separate threads to handle sending and receiving messages simultaneously.


# How to Run the Application:
1.  Run the Server: First, start the server. The server will wait for a client to connect.
    Compile and run the server code (ChatServer.java).
2.  Run the Client: After the server is running, start the client. The client will connect to the server and the chat can begin.
    Compile and run the client code (ChatClient.java).
3.  Chat: Once both are running, the users can chat by typing messages in the console. The messages will be sent between the 
    client and server in real-time.


# Running:
1.  Once the code is compiled, you can run the program using the java command.