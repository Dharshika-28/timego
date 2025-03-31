// Function to switch between tabs   --contact page
function openTab(tabId) {
    const tabs = document.querySelectorAll('.tab-pane');
    tabs.forEach(tab => tab.classList.remove('active'));
    const selectedTab = document.getElementById(tabId);
    selectedTab.classList.add('active');
    
    const navLinks = document.querySelectorAll('.nav-link');
    navLinks.forEach(link => link.classList.remove('active'));
    document.querySelector(`[onclick="openTab('${tabId}')"]`).classList.add('active');
}

// Function to open modals
function openModal(modalId) {
    document.getElementById(modalId).style.display = 'flex';
}

// Function to close modals
function closeModal(modalId) {
    document.getElementById(modalId).style.display = 'none';
}


//set the validation

document.getElementById('login').onclick = function(){
    const login_container = document.getElementById('login-container');   
    login_container.style.display = "none";
    const login = document.getElementById('signin-container');  
    login.style.display='block';
    const footer = document.getElementById('footer')
    footer.style.display = "none";
}


document.getElementById('signup').onclick = function(){
    const login_container = document.getElementById('login-container');
    login_container.style.display = "none";
    const signup = document.getElementById('signup-container');
    signup.style.display='block';
    const footer = document.getElementById('footer');
    footer.style.display = "none";
}


document.getElementById('signup_again').onclick = function(){
    const footer = document.getElementById('footer')
    footer.style.display = "none";
    const login_container = document.getElementById('login-container');
    login_container.style.display = "none";
    const signup = document.getElementById('signup-container');
    signup.style.display ="block";
    const login = document.getElementById('signin-container');
    login.style.display = "none";
}

document.getElementById('login_again').onclick = function(){
    const footer = document.getElementById('footer');
    footer.style.display = "none";
    const login_container = document.getElementById('login-container');
    login_container.style.display = "none";
    const signup = document.getElementById('signup-container');
    signup.style.display = "none";
    const login = document.getElementById('signin-container');
    login.style.display = "block";
}

const modal = document.getElementById('modal');
    const openModalBtn = document.getElementById('openModalBtn');
    const closeModalBtn = document.getElementById('closeModalBtn');
    const closeBtn = document.getElementById('closeBtn');

openModalBtn.onclick = () => modal.style.display = 'flex';
closeModalBtn.onclick = closeBtn.onclick = () => modal.style.display = 'none';

document.addEventListener('DOMContentLoaded', function() {
    const generateButton = document.getElementById('generate');
    
    if (generateButton) {
        generateButton.onclick = function() {
            // Getting values as variables:
            const Password = 9; // Password length
            const Lowercase = true;
            const Uppercase = true;
            const Numbers = true;
            const result = document.getElementById('code');

            // Character sets for password generation
            const lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
            const uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            const numbersChars = "0123456789";

            // Empty container for allowed characters
            let allowedChars = "";
            let password = "";

            // Adding character sets based on user input
            allowedChars += Lowercase ? lowercaseChars : "";
            allowedChars += Uppercase ? uppercaseChars : "";
            allowedChars += Numbers ? numbersChars : "";

            // Validate password length
            if (Password <= 0) {
                return result.textContent = `(password length must be at least 1)`;
            }

            // Validate at least one character set is selected
            if (allowedChars.length === 0) {
                return result.textContent = `(at least one set of character needs to be selected)`;
            }

            // Generate the password
            for (let i = 0; i < Password; i++) {
                const randomIndex = Math.floor(Math.random() * allowedChars.length);
                password += allowedChars[randomIndex];
            }

            // Display the generated password
            return result.textContent = `Your code: ${password}`;
        };
    } else {
        console.error("Button with id 'generate' not found.");
    }
});


