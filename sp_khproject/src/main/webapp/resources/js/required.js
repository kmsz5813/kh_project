/**
 * 
 */
window.onload = function() {
	var form = document.forms[0];
	form.addEventListener("submit", formCheck);
	errorFocusing();
}

function formCheck(e) {
	for(element of e.target.querySelectorAll("[data-required]")) {
		if(element.value === "") {
			e.preventDefault();
			if(!document.querySelector(".required-box")) {
				requiredBox(element, element.dataset.required);
				element.focus();
			}
			return false;
		}
	}
	this.submit();
}

function requiredBox(element, message) {
	var box = document.createElement("div");
	box.setAttribute("class", "required-box");
	box.innerText = message;
	element.parentElement.append(box);
	
	box.style.left = 16 + "px";
	box.style.top = element.offsetHeight + element.offsetTop + "px";
	box.setAttribute("class", "required-box show");
	
	setTimeout(function() {
		box.remove();
	}, 1500);
}

function errorFocusing() {
	var error = document.getElementsByClassName("input-label-error")[0];
	if(error !== undefined) {
		error.parentElement.getElementsByClassName("input-text")[0].focus();
	}
}