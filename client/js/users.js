document.getElementById("loadUsers").addEventListener("click", async function() {
   const userListElement = document.getElementById("userList");
   userListElement.innerHTML = ""; // Очищаем список перед загрузкой новых данных

   try {
       // Запрос к серверу
       const response = await fetch('http://localhost:8080/user/getAll');
       if (!response.ok) throw new Error('Failed to fetch users');
       
       const users = await response.json(); // Парсим JSON
       users.forEach(user => {
           // Создаем элемент списка для каждого пользователя
           const listItem = document.createElement("li");
           listItem.textContent = `ID: ${user.id}, Name: ${user.username}, Password: ${user.password}, Email: ${user.email}, Role: ${user.role}`;
           userListElement.appendChild(listItem);
       });
   } catch (error) {
       console.error('Error:', error);
       userListElement.innerHTML = "<li>Error loading users</li>";
   }
});