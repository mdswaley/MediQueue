const BASE_URL = "http://localhost:8080/appointment";

loadAppointments();

document
    .getElementById("appointmentForm")
    .addEventListener("submit", saveAppointment);

// -------------------- Save Appointment --------------------

async function saveAppointment(e) {

    e.preventDefault();

    const appointment = {

        patientName: document.getElementById("patientName").value,
        age: document.getElementById("age").value,
        mobileNumber: document.getElementById("mobileNumber").value,
        email: document.getElementById("email").value,
        appointmentDate: document.getElementById("appointmentDate").value,
        doctorOptions: document.getElementById("doctorOptions").value

    };

    const response = await fetch(BASE_URL + "/create", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(appointment)

    });

    if (response.ok) {

        const data = await response.json();

        alert(
            `Appointment Booked Successfully

Token : ${data.tokenNumber}

Time : ${data.appointmentTime}`
        );

        document.getElementById("appointmentForm").reset();

        loadAppointments();

    } else {

        const error = await response.text();

        alert(error);

    }

}

// -------------------- Get All --------------------

async function loadAppointments() {

    const response = await fetch(BASE_URL + "/getAllAppointment");

    const appointments = await response.json();

    let rows = "";

    appointments.forEach(ap => {

        rows += `

        <tr>

            <td>${ap.tokenNumber}</td>

            <td>${ap.patientName}</td>

            <td>${ap.doctorOptions}</td>

            <td>${ap.appointmentDate}</td>

            <td>${ap.appointmentTime}</td>

            <td>

                <button
                    class="btn btn-danger btn-sm"
                    onclick="deleteAppointment(${ap.id})">

                    Delete

                </button>

            </td>

        </tr>

        `;

    });

    document.getElementById("appointmentTable").innerHTML = rows;

}

// -------------------- Delete --------------------

async function deleteAppointment(id) {

    const confirmDelete = confirm("Delete Appointment?");

    if (!confirmDelete) return;

    await fetch(BASE_URL + "/delete/" + id, {

        method: "DELETE"

    });

    loadAppointments();

}