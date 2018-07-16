const func = () => {
	alert("func!");
	console.log('hi!');
};

const ajaxFunc = () => {
	const oReq = new XMLHttpRequest();
	oReq.addEventListener('load', () => {
		console.log("now ajax got!");
		console.log(oReq.response);
		console.log('json!');
		const data = JSON.parse(oReq.response);
		console.log('roleid : ', data.roleId);
		console.log('description : ', data.description);
	});

	oReq.open('POST', '/secondproject/ajax');
	oReq.send();
}

const ajaxPromise = () => new Promise((resolve, reject) => {
	const xhr = new XMLHttpRequest();

	xhr.onload = () => {
		if (xhr.status >= 200 && xhr.status < 300) {
			resolve(JSON.parse(xhr.response));
		} else {
			reject(xhr.statusText);
		}
	}
	xhr.onerror = () => reject(xhr.statusText);
	
	xhr.open('POST', '/secondproject/ajax');
	xhr.send();
});

const onButtonClick = () => {
	ajaxPromise().then(role => {
		console.log('roleid : ', role.roleId);
		console.log('description : ', role.description);
	}).catch(e => console.log(e));
}

const onSubmit = (e) => {
	console.log(e);
	
	const form = document.forms('todo-form');
	
	const title = form['title'].value;
	
	if (title.length > 1) {
		console.log('title is :', title);
	}
}

const changeType = (e) => {
	console.log('change type!');
	console.log(e);
}