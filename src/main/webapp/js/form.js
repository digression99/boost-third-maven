/**
 *
 */

const onFormSubmitPromise = (formData) => new Promise((resolve, reject) => {
    const xhr = new XMLHttpRequest();
	xhr.open('POST', '/secondproject/todo', true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onload = () => {
        if (xhr.status >= 200 && xhr.status < 300) {
            // console.log('data received : ', JSON.stringify(xhr.response));
			resolve(JSON.parse(xhr.response));
		} else {
			reject(xhr.statusText);
		}
    }

    const payload = JSON.stringify(formData);
	xhr.send(payload);
});

const onFormSubmit = (event) => {
	event.preventDefault();

	const form = event.target;
    const formData = {
        title : form.title.value,
        name : form.name.value,
        sequence : form.sequence.value
    };

    const backButton = form.querySelector('.form-back-button');
    const formControllerButtons = form.querySelectorAll('.form-controller-button');
    const fieldSet = form.querySelector('.form-fieldset');
    // a.disabled {
    //    pointer-events: none;
    //    cursor: default;
    // }

    fieldSet.disabled = true;
    backButton.classList.add("form-back-button-disabled");
    Array.from(formControllerButtons).map(button => button.disabled = true);

    onFormSubmitPromise(formData).then(response => {
        if (response.insertCount === 1) {
            console.log('succeed.');
            messagePrinter.printMessage("Successfully saved.", "success");
            setTimeout(() => {
                // backButton.classList.remove("form-back-button-disabled");
                // backButton.disabled = false;

                // backButton.style['pointer-events'] = 'auto';
                // backButton.style['cursor'] = 'pointer';
                // backButton.style['opacity'] = 1;
                // Array.from(formControllerButtons).map(button => button.disabled = false);
                window.location.href = '/secondproject';
            }, 2000);
        } else {

            fieldSet.disabled = false;
            backButton.classList.remove("form-back-button-disabled");
            Array.from(formControllerButtons).map(button => button.disabled = false);
            // need error message.
            messagePrinter.printMessage("Failed to save your todo.", "error");
            console.log('failed to insert.');
        }
    }).catch(err => console.log(err));
};
