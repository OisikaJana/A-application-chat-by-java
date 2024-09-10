# A-application-chat-by-java


# Architecture:
Server: One user acts as the server, which waits for connections from the client.
Client: The other user connects to the server and can start sending and receiving messages.
Both the client and server will run in separate threads to handle sending and receiving messages simultaneously.


# How to Run the Application:
1.  Run the Server: First, start the server. The server will wait for a client to connect.
    Compile and run the server code (ChatServer.java).