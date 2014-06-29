package net.bohush.exercises.chapter33;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Exercise06s {
	private ArrayList<Address> addresses = new ArrayList<>();
	int current = 0;
	int activeClients = 0;
	
	public Exercise06s() {		
		/*addresses.add(new Address("Viktor", "Shevchenka", "Lviv", "Lviv", "12343"));
		addresses.add(new Address("Andriy", "Stysa", "Ternopil", "Ternopil", "65456"));
		addresses.add(new Address("Slavik", "Ivasuka", "Kyiv", "Kyiv", "87878"));
		*/
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8000);
			while (true) {
				if(activeClients >= 2) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					Socket socket = serverSocket.accept();		
					HandleClient task = new HandleClient(socket);
					activeClients++;
					new Thread(task).start();
				}
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
	
	}

	public static void main(String[] args) {
		new Exercise06s();
	}
	
	class HandleClient implements Runnable {
		private Socket socket;
		
		public HandleClient(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			Scanner inputFromClient;
			PrintWriter outputToClient;
			try {
				inputFromClient = new Scanner(socket.getInputStream());
				outputToClient = new PrintWriter(socket.getOutputStream());
				
				while (true) {
					int command = Integer.parseInt(inputFromClient.next());
					switch (command) {
					case 1:
						Address address = new Address(inputFromClient.next(), inputFromClient.next(), inputFromClient.next(), inputFromClient.next(), inputFromClient.next());
						addresses.add(address);
						current = 0;
						break;
					case 2:
						if(!addresses.isEmpty()) {
							outputToClient.write("1 ");
							outputToClient.flush();
							addresses.get(0).send(outputToClient);
							current = 0;							
						} else {
							outputToClient.write("0 ");
							outputToClient.flush();
						}
						break;
					case 3:
						if(!addresses.isEmpty()) {
							outputToClient.write("1 ");
							outputToClient.flush();
							if(current < addresses.size() - 1) {
								current++;
							}
							addresses.get(current).send(outputToClient);
						} else {
							outputToClient.write("0 ");
							outputToClient.flush();
						}
						break;
					case 4:
						if(!addresses.isEmpty()) {
							outputToClient.write("1 ");
							outputToClient.flush();
							if(current > 0) {
								current--;
							}
							addresses.get(current).send(outputToClient);
						} else {
							outputToClient.write("0 ");
							outputToClient.flush();
						}						
						break;
					case 5:
						if(!addresses.isEmpty()) {
							outputToClient.write("1 ");
							outputToClient.flush();
							addresses.get(addresses.size() - 1).send(outputToClient);
							current = addresses.size() - 1;							
						} else {
							outputToClient.write("0 ");
							outputToClient.flush();
						}
						break;
					default:
						break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NoSuchElementException e) {
				activeClients--;
			}
		}
		
	}
	
	static class Address {
		String name;
		String street;
		String city;
		String state;
		String zip;
		
		public void send(PrintWriter outputToClient) {
			outputToClient.write(name + " ");
			outputToClient.write(street + " ");
			outputToClient.write(city + " ");
			outputToClient.write(state + " ");
			outputToClient.write(zip + " ");
			outputToClient.flush();
		}
		
		public Address(String name, String street, String city, String state, String zip) {
			this.name = name;
			this.street = street;
			this.city = city;
			this.state = state;
			this.zip = zip;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getStreet() {
			return street;
		}
		public void setStreet(String street) {
			this.street = street;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getZip() {
			return zip;
		}
		public void setZip(String zip) {
			this.zip = zip;
		}
		
		
	}

}
