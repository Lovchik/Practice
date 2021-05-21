(async () => {
    console.log('master script ')
    const pass = getCookie('pass')
    const phone = getCookie('phone')
    console.log('pass', pass)
    console.log('phone', phone)
    const id = window.location.search.split('=')[1]
    console.log('id',id)
    fetch('http://localhost:8000/person').then(res => res.json()).then(res => {
        console.log('res', res)
        const master = res.filter(item => item.id == id)[0]
        console.log('master', master)
        document.querySelector('#name').innerText = `${master.firstName} ${master.lastName}`
        document.querySelector('#phone').innerText = master.phoneNumber
        document.querySelector('#workshop-name').innerText = master.workPlace.workshopName
        document.querySelector('#workshop-address').innerText = master.workPlace.address
        fetch(`http://localhost:8000/contract/master/${id}`).then(res => res.json()).then(res => {
            console.log(res)
            const container = document.querySelector('#table')
            res.forEach(item => {
                const tr = document.createElement('tr')
                const row = document.createElement('th')
                row.className = 'row'
                row.innerText = item.id
                const ftd = document.createElement('td')
                const std = document.createElement('td')
                ftd.innerText = item.status
                const btn = document.createElement('button')
                btn.type = 'button'
                btn.className = 'btn btn-dark'
                btn.innerText = 'Выполнить'
                btn.disabled = item.status === 'COMPLETED'
                btn.onclick = () => {
                    console.log('clicked')
                    fetch(`http://localhost:8000/person/${id}`, {method: 'PUT'}).then(res => res.json).then(async res => {
                        console.log('res of put', res)
                        await alert('STATUS UPDATE')
                        location.reload()
                    })
                }
                std.append(btn)
                tr.append(row, ftd,std)
                container.append(tr)
            })

        })

    })
})()

function getCookie(name) {
    let matches = document.cookie.match(new RegExp(
        "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}