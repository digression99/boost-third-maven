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

	oReq.open('POST', 'http://localhost:8080/secondproject/ajax');
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
	
	xhr.open('POST', 'http://localhost:8080/secondproject/ajax');
	xhr.send();
});

const onButtonClick = () => {
	ajaxPromise().then(role => {
		console.log('roleid : ', role.roleId);
		console.log('description : ', role.description);
	}).catch(e => console.log(e));
}