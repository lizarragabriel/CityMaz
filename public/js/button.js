const formEdit = document.querySelector('#formEdit')
      formEdit.addEventListener('submit', async(e) => {
        e.preventDefault()
        //const name = formularioEditar.elements['name'].value
        const firstName = document.querySelector('#firstName').value
        const lastName = document.querySelector('#lastName').value
        const email = document.querySelector('#email').value
        const id = formEdit.dataset.id
        try {
            const data = await fetch(`/employees/${id}`, {
                method: 'put',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({firstName, lastName, email})
            })
            const res = await data.json()
            
            if(res.estado){
                window.location.href = '/employees'
            }else{
                console.log(res)
            }
        } catch (error) {
            console.log(error)
        }
    })