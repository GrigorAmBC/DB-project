function createGenderAndUpdate() {
    setTimeout(doCallAndUpdate, 500);
}

function doCallAndUpdate() {
    const http = new XMLHttpRequest();
    const url = "http://localhost:8080/genders.json"
    http.open("GET", url);

    http.send();
    http.onreadystatechange = (e) => {
        const genderSelector = document.querySelector('#gender')
        const length = genderSelector.options.length
        if (length > 0) {
            for (i = length - 1; i >= 0; i--) {
                genderSelector.options.remove(i)
            }
        }

        const genderArray = JSON.parse(http.response)
        genderArray.forEach(function (value) {
            console.log(value.id)
            var opt = document.createElement('option');
            opt.value = value.id;
            opt.innerHTML = value.name;
            genderSelector.appendChild(opt);
        })
    }
}