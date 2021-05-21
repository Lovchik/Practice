let userCoordinates
let carServices = [];
const sbmtButton = document.querySelector('#sbmt123');
console.log('sbmtButton',sbmtButton);
(async () => {
    let myLatitude, myLongitude
    navigator.geolocation.getCurrentPosition((q) => {
        myLatitude = q.coords.latitude
        myLongitude = q.coords.longitude
        userCoordinates = `${q.coords.latitude}, ${q.coords.longitude}`
    })
    fetch('http://localhost:8000/workshop').then(res => res.json()).then(res => {
        const marks = res.map(item => ({
            id: item.id,
            name: item.workshopName,
            coordinate: item.coordinates.split(', ')
        }))

        ymaps.ready(() => {
            var myMap = new ymaps.Map("map", {
                center: marks[0].coordinate,
                zoom: 5, //10
                controls: []
            }, {
                searchControlProvider: 'yandex#search'
            });

            for (let i = 0; i < marks.length; i++) {
                myMap.geoObjects.add(new ymaps.Placemark(marks[i].coordinate, {balloonContent: `<a href="/workshop.html?id=${marks[i].id}">${marks[i].name}</a>`}))
            }

            var location = ymaps.geolocation;

            location.get({
                mapStateAutoApply: true
            })
                .then(
                    function (result) {
                        var userAddress = result.geoObjects.get(0).properties.get('text');
                        userCoordinates = result.geoObjects.get(0).geometry.getCoordinates();
                        result.geoObjects.get(0).properties.set({
                            balloonContentBody: 'Адрес: ' + userAddress +
                                '<br/>Координаты:' + userCoordinates
                        });
                        userCoordinates = userCoordinates.join(', ')
                        myMap.geoObjects.add(result.geoObjects)
                    },
                    function (err) {
                        console.log('Ошибка: ' + err)
                    }
                );

        });
    }).catch(err => console.log('err in get /workshop req', err))
    fetch('http://localhost:8000/carServices').then(res => res.json()).then(res => {
        const accordionBody = document.querySelector('.accordion-body')
        res.forEach(item => {
            const input = document.createElement('input')
            input.type = 'checkbox'
            input.name = 'checkbox'
            input.id = item.name
            input.checked = false
            input.className = 'input_service'
            input.onchange = (e) => {
                if (e.target.checked) carServices.push({name:e.target.id})
                else carServices = carServices.filter(item1 => item1.name !== e.target.id)
                sbmtButton.disabled = !carServices.length
            }
            const div = document.createElement('div')
            div.innerText = item.name
            const label = document.createElement('label')
            label.append(input)
            label.append(div)
            label.className = 'checkbox-label'
            label.for = item.name
            accordionBody.append(label)
        })
        document.querySelector('.accordion-list').classList.remove('hidden')
    }).catch(err => console.log('err in get /carService req', err))

})()

function createOffer() {
    const result = {
        client: {
            coordinates: userCoordinates
        },
        carServices: [...carServices]
    }
    const cheapest = document.querySelector('#cheapest')
    if (cheapest.checked) {
        fetch('http://localhost:8000/workshop/cheaper', {method: "POST",headers:{
                "Content-Type": "application/json"
            }, body: JSON.stringify(result)}).then(res => res.json()).then(res => {
            console.log('res post workshop' ,res)
            window.location.replace(`http://localhost:8000/workshop.html?id=${res.id}`);
        }).catch(err => console.log('post workshop err', err))
    }
    else {
        fetch('http://localhost:8000/workshop', {method: "POST",headers:{
                "Content-Type": "application/json"
            }, body: JSON.stringify(result)}).then(res => res.json()).then(res => {
            console.log('res post workshop' ,res)
            window.location.replace(`http://localhost:8000/workshop.html?id=${res.id}`);
        }).catch(err => console.log('post workshop err', err))
    }
}