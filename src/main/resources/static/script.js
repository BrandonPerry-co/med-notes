
document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById('medNotesForm');
    form.addEventListener('submit', handleSubmit);
    fetchAndDisplayNotes();
});

//Add patient notes
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

//Fetch all notes from DB
document.addEventListener('DOMContentLoaded', (event) => {
    fetchAllPatients();
});

function fetchAllPatients() {
    fetch('http://localhost:8082/patHistory/getAll')
        .then(response => {
            if (!response.ok) {
                throw new Error(`Failed with status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            let patientNoteListDiv = document.getElementById('patientNoteList');
            let html = '';

            data.forEach(note => {
                html += `<div><strong>Patient ID:</strong> ${note.patId}<br>`;
                html += `<strong>ID:</strong> ${note.id}</div><br>`;
                html += `<strong>Note:</strong> ${note.note}</div><hr>`;
            });

            patientNoteListDiv.innerHTML = html;
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

document.getElementById('medicalNoteUpdateForm').addEventListener('submit', function(event) {
    event.preventDefault();
    updatePatient();
});

// Update patient note
window.updatePatient = updatePatient;

function updatePatient() {
    console.log('updatePatient function called');

    let patId = document.getElementById('id').value;
    let note = document.getElementById('note').value;

    let userDetails = {
        id: patId,
        note: note
    };

    fetch(`http://localhost:8082/patHistory/${patId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userDetails)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`Failed with status: ${response.status}`);
        }
        return response.json();
    })
    .then(data => {
        let infoDiv = document.getElementById('updatedPatientInfo');
        infoDiv.innerHTML = `
            Patient Id: ${data.id}<br>
            Note: ${data.note}
        `;
    })
    .catch(error => {
        console.error('Error:', error);
    });
}