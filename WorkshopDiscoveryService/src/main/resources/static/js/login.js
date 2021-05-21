function login() {
    var pass = document.getElementById('pass').value;
    var phone =document.getElementById('phone').value;
    let   result = {
        "password": pass,
        "phoneNumber": phone
    }
    fetch('http://localhost:8000/person', {method: "POST",headers:{
            "Content-Type": "application/json"
        }, body: JSON.stringify(result)}).then(res => res.json()).then(res => {
        console.log('res post workshop' ,res)
        setCookie('pass', `${pass}`, {secure: true, 'max-age': 3600});
        setCookie('phone', `${phone}`, {secure: true, 'max-age': 3600});
        if(res.role==="CLIENT"){
            window.location.replace(`http://localhost:8000/mainPage.html`);
        }
        if (res.role==="ADMIN"){
            window.location.replace(`http://localhost:8000/admin.html?id=${res.workPlace.id}`);
        }
        if(res.role==="MASTER"){
            window.location.replace(`http://localhost:8000/master.html?id=${res.id}`);

        }
    }).catch(err => console.log('post workshop err', err))
    console.log('result', result)
    function setCookie(name, value, options = {}) {
        options = {
            path: '/',
            ...options
        };
        if (options.expires instanceof Date) {
            options.expires = options.expires.toUTCString();
        }
        let updatedCookie = encodeURIComponent(name) + "=" + encodeURIComponent(value);
        for (let optionKey in options) {
            updatedCookie += "; " + optionKey;
            let optionValue = options[optionKey];
            if (optionValue !== true) {
                updatedCookie += "=" + optionValue;
            }
        }
        document.cookie = updatedCookie;
    }

}