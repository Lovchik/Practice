console.log('in script')
let chosenServices = []
const sbmtButton = document.querySelector('#sbmt-btn')
sbmtButton.onclick = () => {
    fetch(`http://localhost:8000/requests`, {
        method: "POST", headers: {
            "Content-Type": "application/json"
        }, body: JSON.stringify({
            carServices: chosenServices.map(item => ({name: item})),
            workshop: {id}
        })
    })
        .then(res => res.json)
        .then(async res => {
            console.log('response', res)
            await alert('Your request created!')
            window.location.replace(`http://localhost:8000/mainPage.html`);
        }).catch(err => console.log('err poss /requests', err))
}
const id = window.location.search.split('=')[1]
fetch(`http://localhost:8000/workshop/${id}`).then(res => res.json()).then(res => {
    const table = document.querySelector('#table')
    const elements = res.priceList.map(item => {
        const input = document.createElement('input')
        input.type = 'checkbox'
        input.name = item.carService.name
        input.id = item.carService.name
        input.checked = false
        input.onchange = (e) => {
            console.log('event ', e.target.checked)
            if (e.target.checked) chosenServices.push(e.target.id)
            else chosenServices = chosenServices.filter(item1 => item1 !== e.target.id)
            sbmtButton.disabled = !chosenServices.length
        }
        const tr = document.createElement('tr')
        const row = document.createElement('th')
        row.className = 'row'
        row.innerText = item.carService.name
        const ftd = document.createElement('td')
        const std = document.createElement('td')
        ftd.innerText = item.price
        std.append(input)
        tr.append(row, ftd,std)
        return tr
    })
    elements.forEach(item => table.append(item))
    const title = document.querySelector('#title')
    title.innerHTML = res.workshopName
    const address = document.querySelector('#address')
    address.innerHTML = res.address
    console.log('address', res.address)
    console.log('table', table)
    console.log('elements', elements)


    ymaps.ready(() => {
        ymaps.geolocation.get({mapStateAutoApply: false}).then((result) => {
            var multiRoute = new ymaps.multiRouter.MultiRoute({
                referencePoints: [
                    result.geoObjects.get(0).geometry.getCoordinates(),
                    res.coordinates.split(', ')
                ],
                params: {
                    results: 2
                }
            }, {
                boundsAutoApply: true
            });
            var myMap = new ymaps.Map('map', {
                center: [53.90201563767497, 27.556874353996726],
                zoom: 7,
                controls: []
            }, {
                buttonMaxWidth: 300
            });
            myMap.geoObjects.add(multiRoute);
        })

    })
})
