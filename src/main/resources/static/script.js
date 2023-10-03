//

document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById('medNotesForm');
    form.addEventListener('submit', handleSubmit);
    fetchAndDisplayNotes();
});

function handleSubmit(event) {
    event.preventDefault();

    const patId = document.getElementById('patId').value;
    const note = document.getElementById('note').value;

    const payload = {
        patId: patId,
        note: note
    };

    fetch('http://localhost:8082/patHistory/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    })
    .then(response => response.json())
    .then(data => {
        alert(`Patient note added successfully!\n\nPatient Id: ${data.patId} \nNote: ${data.note}`);
        fetchAndDisplayNotes();
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Failed to add patient notes.');
    });
}

function fetchAndDisplayNotes() {
    // Fetch all patient notes and display them in 'patientNoteList' div
    // This function's implementation depends on the API structure.
}


//// Posting A New Patient this works
//document.addEventListener("DOMContentLoaded", function() {
//    const form = document.getElementById('medNotesForm');
//    form.addEventListener('submit', handleSubmit);
//});
//
//function handleSubmit(event) {
//    event.preventDefault();
//
//    let patId = document.getElementById('patId').value;
//    let note = document.getElementById('note').value;
//
//    let payload = {
//        patId: patId,
//        note: note
//    };
//
//    fetch('http://localhost:8082/patHistory/add'{
//        method: 'POST',
//        headers: {
//            'Content-Type': 'application/x-www-form-urlencoded'
//        },
//        body: new URLSearchParams(payload)
//    })
//    .then(response => response.json())
//    .then(data => {
//        alert(`Patient note added successfully!\n\nPatient Id: ${data.patId} \nNote: ${data.note}`);
//    })
//    .catch(error => {
//        console.error('Error:', error);
//        alert('Failed to add patient notes.');
//    });
//}



document.getElementById('medicalNoteUpdateForm').addEventListener('submit', function(event) {
    event.preventDefault();
    updatePatientNotes();
});

// Update patient note

window.updatePatient = updatePatient;

function updatePatient() {
console.log('updatePatient function called');
    let patId = document.getElementById('patId').value;
    let note = document.getElementById('note').value;

    let userDetails = {
        patId: patId,
        note: note
    };

    Object.keys(userDetails).forEach(key => {
        if (!userDetails[key]) delete userDetails[key];
    });

    fetch(`http://localhost:8082/patHistory/${patId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userDetails)
    })
    .then(response => response.json())
    .then(data => {
        let infoDiv = document.getElementById('updatedPatientInfo');
        infoDiv.innerHTML = `
            Patient Id: ${data.patId}<br>
            Note: ${data.note}
        `;
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

//document.addEventListener("DOMContentLoaded", function() {
//    fetchPatients();
//});
//
//
//
//function fetchPatients() {
//    fetch("http://localhost:8081/patient/findAll")
//    .then(response => {
//        if (!response.ok) {
//            throw new Error("Network response was not ok");
//        }
//        return response.json();
//    })
//    .then(patients => {
//        const patientsList = document.getElementById("patientsList");
//
//        // Check if patientsList exists
//        if (!patientsList) {
//            console.warn("Element with ID 'patientsList' not found in the DOM.");
//            return;
//        }
//
//        patients.forEach(patient => {
//            const patientInfo = document.createElement("div");
//            patientInfo.textContent = `
//                ID: ${patient.id}
//                Address: ${patient.address},
//                DOB: ${patient.dob},
//                Family: ${patient.family},
//                Given: ${patient.given},
//                Sex: ${patient.sex},
//                Phone: ${patient.phone}
//            `;
//            patientsList.appendChild(patientInfo);
//        });
//    })
//    .catch(error => {
//        console.error("There was a problem with the fetch operation:", error.message);
//    });
//}