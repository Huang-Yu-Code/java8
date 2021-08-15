# Socket

## Server

创建Server端:`ServerSocket serverSocket = new ServerSocket(port);`

获取连接请求:`Socket socket = serverSocket.accept();`

接收流:`socket.getInputStream();`

关闭流:`socket.shutdownOutput();`

发送流:`socket.getOutputStream();`

关闭流:`socket.shutdownInput();`

关闭socket,同时关闭流:`socket.close();`

## Client

创建Client端:`Socket socket = new Socket(host, port);`

发送流:`socket.getOutputStream();`

关闭流:`shutdownOutput();`

接收流:`socket.getInputStream();`

关闭流:`socket.shutdownInput();`

关闭socket,同时关闭流:`socket.close();`