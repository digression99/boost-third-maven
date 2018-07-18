/**
 *
 */

 const getNextType = type => {
     return type == "TODO" ? "DOING" : "DONE";
 }

const getNextList = (typeName) => {
	if (typeName === "TODO")
		return document.querySelector("#types-doing-list");
	else if (typeName === "DOING")
		return document.querySelector("#types-done-list");
	else return null;
}

const changeNodeList = (el, type) => {
    const nextList = getNextList(type);
    if (type == "DOING") {
		const aside = el.querySelector('.list-item-side');
		aside.removeChild(aside.querySelector('.list-item-button'));
    }
    nextList.appendChild(el);
}

const onChangeType = (el) => {
    const input = el.closest('.list-item-side').querySelector('input');
    const id = input.getAttribute('data-todo-id');
    const type = input.getAttribute('data-todo-type');

    const changeType = (id, type) => new Promise((resolve, reject) => {
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
    });

	changeType(id, type).then(response => {
		// do the dom manipulation.
        const listItem = el.closest('.list-item');
        input.setAttribute('data-todo-type', getNextType(type));

		changeNodeList(listItem, type);
		console.log(response);
	}).catch(err => {
		// show error message to the user.
		console.log(err);
	})
}
