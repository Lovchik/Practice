(async () => {
    const requests = document.querySelector('#requests')
    const requestContainer = document.querySelector('.requests')
    const contracts = document.querySelector('#contracts')
    const contractsContainer = document.querySelector('.contracts')
    const id = window.location.search.split('=')[1]
    fetch(`http://localhost:8000/requests/workshop/${id}`).then(res => res.json()).then(res => {
        console.log('res requests', res)
        res.forEach(item => {
            requestContainer.style.display = 'block'
            const tr = document.createElement('tr')
            const row = document.createElement('th')
            row.className = 'row'
            row.innerText = item.id
            const ftd = document.createElement('td')
            const std = document.createElement('td')
            const btn1 = document.createElement('button')
            btn1.type = 'button'
            btn1.className = 'btn btn-dark'
            btn1.innerText = 'Удалить'
            btn1.onclick = () => {
                console.log('clicked')
                fetch(`http://localhost:8000/person/request/${item.id}`, {method: 'DELETE'}).then(res => res).then(async res => {
                    console.log('res of delete', res)
                    if (res.status === 207) await alert('Not DELETED')
                    if (res.status === 200) await alert('Deleted')
                    location.reload()
                }).catch(err => console.log('err in delete request',err))
            }
            const btn = document.createElement('button')
            btn.type = 'button'
            btn.className = 'btn btn-dark'
            btn.innerText = 'Создать'
            btn.disabled = item.status === 'COMPLETED'
            btn.onclick = () => {
                console.log('clicked')
                fetch(`http://localhost:8000/contract/request/${item.id}`, {method: 'POST'}).then(res => res).then(async res => {
                    console.log('res of post', res)
                    if (res.status === 204) await alert('No free maters')
                    if (res.status === 200) await alert('Success created')
                    location.reload()
                }).catch(err => console.log('err in post request', err))
            }
            ftd.append(btn)
            std.append(btn1)
            tr.append(row, ftd, std)
            requests.append(tr)
        })
    })
    fetch(`http://localhost:8000/contract/workshop/${id}`).then(res => res.json()).then(res => {
        console.log('res requests', res)
        res.forEach(item => {
            contractsContainer.style.display = 'block'
            const tr = document.createElement('tr')
            const row = document.createElement('th')
            row.className = 'row'
            row.innerText = item.id
            const ftd = document.createElement('td')
            ftd.innerText = item.status
            tr.append(row, ftd)
            contracts.append(tr)
        })

    })
})()