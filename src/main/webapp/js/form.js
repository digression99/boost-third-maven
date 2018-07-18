/**
 *
 */

class FormHandler {

}

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

const onFormSubmit = event => {
	event.preventDefault();

	const form = event.target;
    const formData = {
        title : form.title.value,
        name : form.name.value,
        sequence : form.sequence.value
    };

    onFormSubmitPromise(formData).then(response => {
        if (response.insertCount === 1) {
            console.log('succeed.');
            setTimeout(() => {
                window.location.href = '/secondproject';
            }, 2000);
        } else {
            // need error message.
            console.log('failed to insert.');
        }
    }).catch(err => console.log(err));
};
