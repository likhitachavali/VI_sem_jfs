// Auto-calculate total fee
let subjects = document.querySelectorAll(".subject");
let totalBox = document.getElementById("total");

subjects.forEach(item => {
    item.addEventListener("change", () => {
        let total = 0;

        subjects.forEach(sub => {
            if (sub.checked) {
                total += parseInt(sub.value);
            }
        });

        totalBox.innerText = "₹" + total;
    });
});

// Optional: form submit
document.getElementById("regForm").addEventListener("submit", function(e){
    e.preventDefault();

    let selectedSubjects = [];
    let totalFee = 0;

    subjects.forEach(sub => {
        if (sub.checked) {

            // Get subject name from label text
            let subjectName = sub.parentElement.innerText.trim();
            selectedSubjects.push(subjectName);

            totalFee += parseInt(sub.value);
        }
    });

    if (selectedSubjects.length === 0) {
        alert("Please select at least one subject.");
        return;
    }

    let studentName = document.getElementById("name").value;

    let resultHTML = `
        <h3>Registration Summary</h3>
        <p><strong>Student Name:</strong> ${studentName}</p>
        <p><strong>Selected Subjects:</strong></p>
        <ol>
            ${selectedSubjects.map(sub => `<li>${sub}</li>`).join("")}
        </ol>
        <p><strong>Total Fee:</strong> ₹${totalFee}</p>
    `;

    let resultBox = document.getElementById("resultBox");
    resultBox.innerHTML = resultHTML;
    resultBox.style.display = "block";
});
