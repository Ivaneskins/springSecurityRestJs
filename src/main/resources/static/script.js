// const dataPlaceholder = document.getElementById('data-placeholder');
//
//
// document.addEventListener("DOMContentLoaded", getData);
//
// function getData() {
//     fetch('/user/rest/')
//         .then(response => response.json())
//         .then(data => {
//             const name = data.username;
//             const html = "Hello, " + name ;
//             dataPlaceholder.innerHTML = html;
//         })
//         .catch(error => {
//             console.error('Произошла ошибка: ', error);
//         })
// }
//
//

const dataPlaceholder = document.getElementById('data-placeholder');

const id = document.getElementById('id');
const userName = document.getElementById('userName');
const lastName = document.getElementById('lastName');
const age = document.getElementById('age');
const email = document.getElementById('email');
const roles = document.getElementById('roles');


document.addEventListener("DOMContentLoaded", getData);


function getData() {
    fetch('http://localhost:8080/user/rest/')
        .then(response => response.json())
        .then(data => {
            id.innerHTML = data.id;
            userName.innerHTML = data.username;
            lastName.innerHTML = data.lastname;
            age.innerHTML = data.age;
            email.innerHTML = data.email;
            roles.innerHTML = data.roles[0].name.replace('ROLE_', '');
            setTimeout(getData, 2000);
        })
        .catch(error => {
            console.error('Произошла ошибка: ', error);
        })
}







