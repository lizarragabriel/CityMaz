const formEdit = document.querySelector("#formEdit");
formEdit.addEventListener("submit", async (e) => {
  e.preventDefault();
  const name = document.querySelector("#name").value;
  const image = document.querySelector("#image").value;
  const description = document.querySelector("#description").value;
  const id = formEdit.dataset.id;
  try {
    const data = await fetch(`/cities/${id}`, {
      method: "put",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ name, image, description }),
    });
    const res = await data.json();

    if (res.estado) {
      window.location.href = "/cities";
    } else {
      console.log(res);
    }
  } catch (error) {
    console.log(error);
  }
});

const btnDelete = document.querySelector("#btnDelete");
btnDelete.addEventListener("click", async () => {
  const id = btnDelete.dataset.id;
  try {
    const data = await fetch(`/cities/${id}`, {
      method: "delete",
    });
    const res = await data.json();
    if (res.estado) {
      window.location.href = "/cities";
    } else {
      console.log(res);
    }
  } catch (error) {
    console.log(error);
  }
});
