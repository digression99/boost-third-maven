
const getList = (node) => {
	return node.parentElement.parentElement;
};

const getNextList = (typeName) => {
	if (typeName === "TODO")
		return document.querySelector("#types-doing-list");
	else if (typeName === "DOING") 
		return document.querySelector("#types-done-list");
	else return null;
}

const getNextType = (typeName) => {
	if (typeName === "TODO")
		return "DOING";
	else if (typeName === "DOING") 
		return "DONE";
	else return null;
}

const func = (e) => {

	// const node = e.parentElement;
	// const type = node.querySelector('input[name=todo-type]');
	// const nextList = getNextList(type);
	// const cloneNode = node.cloneNode(true);
	
	// // manipulate.
	// nextList.appendChild(cloneNode);
	// node.parentElement.removeChild(node);
	// node.remove();

	// console.log(e.parentElement);
	// const cloneLi = e.parentElement.cloneNode(true);
	// console.log(cloneLi.id);
	// console.log(cloneLi.className);
	// const nextList = cloneList.nextElementSibling;

};

const changeNodeList = (e) => {
	const node = e.parentElement.parentElement;
	const type = node.querySelector('input[name=todo-type]').value;

	console.log('in change node list, type : ', type);

	const nextList = getNextList(type);
	const cloneNode = node.cloneNode(true);

	if (type === "DOING") {
		const aside = cloneNode.querySelector('.list-item-side');
		aside.removeChild(aside.querySelector('.list-item-button'));
	}
	cloneNode.querySelector('input[name=todo-type]').value = getNextType(type);
	
	// manipulate.
	nextList.appendChild(cloneNode);
	node.parentElement.removeChild(node);
}

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

const onChangeType = (e) => {
	changeType(e).then(response => {
		// do the dom manipulation.
		changeNodeList(e);
		console.log(response);
	}).catch(err => {
		// show error message to the user.
		console.log(err);
	})
}

const changeType = (e) => new Promise((resolve, reject) => {
	// method 1 to get the id.
	const id = e.parentElement.querySelector('input[name=todo-id]').value;
	// const id = e.previousElementSibling.value;
	const xhr = new XMLHttpRequest();
	xhr.onload = () => {
		if (xhr.status >= 200 && xhr.status < 300) {
			resolve(JSON.parse(xhr.response));
		} else {
			reject(xhr.statusText);
		}
	}
	xhr.onerror = () => reject(xhr.statusText);
	xhr.open('POST', '/secondproject/todo/type/' + id);
	xhr.send();
})