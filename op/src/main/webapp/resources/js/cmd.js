$(document)
		.ready(
				function() {
					var history = [];
					var historyIndex = 1;
					var lastPath = null;
					var lastHtml = null;
					var fileList = [];
					function loadFS(lastPath) {
						var uuid = $("#uuid-input").val();
						$.ajax({
							url : "../deploy/cmd/login",
							type : "POST",
							data : {
								uuid : uuid,
								lastPath : lastPath
							},
							cache : false,
							dataType : "text",
							success : function(data) {
								add(data);
							},
							error : function() {
							}
						});
					}
					;

					function add(data) {
						begin(null, data);
					}

					function begin(element, data) {
						var parentElement = element || document.body;
						var dataObj = JSON.parse(data);
						lastPath = dataObj.lastPath;
						fileList = dataObj.filename;
						this.div = document.createElement('div');
						this.div.classList.add('jsterm');
						parentElement.appendChild(this.div);

						var output = document.createElement('div'), stdout = document
								.createElement('span');
						stdout.setAttribute("class", "prompt");
						stdout.id = 'stdout';

						var root = document.createElement('span');
						root.setAttribute("class", "user");
						root.innerText = dataObj.root;

						var pwd = document.createElement('span');
						pwd.setAttribute("class", "cwd");
						pwd.innerText = dataObj.path;

						var node = document.createTextNode(":");
						var node2 = document.createTextNode("$ <span/>");
						var nodeSpan = document.createElement('span');
						nodeSpan.innerText = "$ ";
						stdout.appendChild(root);
						stdout.appendChild(node);
						stdout.appendChild(pwd);
						stdout.appendChild(nodeSpan);

						output.appendChild(stdout);
						lastHtml = output.innerHTML;

						this.div.appendChild(output);

						toggleBlinker(600);

						window.onkeydown = function(e) {
							var key = (e.which) ? e.which : e.keyCode;

							if (key == 8 || key == 9 || key == 13 || key == 46
									|| key == 38 || key == 40 || e.ctrlKey)
								e.preventDefault();
							handleSpecialKey(key, e);
						};

						window.onkeypress = function(e) {
							typeKey((e.which) ? e.which : e.keyCode);
						};
					}
					function clear() {
						$('.jsterm').remove();
						loadFS(lastPath);
					}
					function parse(data) {
						var output = document.createElement('div'), stdout = document
								.createElement('span');
						var dataObj = JSON.parse(data);
						var result = document.createElement('span');
						result.setAttribute("class", "test");
						result.innerHTML = dataObj.result;

						stdout.appendChild(result);
						resetID('#stdout');
						stdout.id = 'stdout';
						output.appendChild(stdout);
						this.div.appendChild(output);
						var blinker = this.div.querySelector('#blinker');
						if (blinker) {
							blinker.parentNode.removeChild(blinker);
						}
						begin(null, data);

					}

					function resetID(query) {
						var element = this.div.querySelector(query);

						if (element)
							element.removeAttribute('id');
					}

					function execute(fullCommand, html) {
						var array = fullCommand.split("$");
						history.push(array[1].trim());
						if (fullCommand != null
								&& fullCommand.indexOf("clear") > 0) {
							clear();
						} else {
							var uuid = $("#uuid-input").val();
							$.ajax({
								url : "../deploy/excute/cmd",
								type : "POST",
								data : {
									uuid : uuid,
									cmd : fullCommand,
									lastPath : lastPath
								},
								cache : false,
								dataType : "text",
								success : function(data) {
									parse(data);
								},
								error : function() {
								}
							});
						}
					}
					;

					function typeKey(key) {
						var stdout = stdoutT();

						if (!stdout || key < 0x20 || key > 0x7E || key == 13
								|| key == 9)
							return;

						stdout.innerHTML += String.fromCharCode(key);
					}
					var abccmd = null;
					function handleSpecialKey(key, e) {
						var stdout = stdoutT(), parts, pathParts;

						if (!stdout)
							return;
						// Backspace/delete.
						if (key == 8 || key == 46) {
							historyIndex = 1;
							stdout.innerHTML = stdout.innerHTML.replace(/.$/,
									'');
							// Enter.
						} else if (key == 13) {
							historyIndex = 1;
							execute(stdout.innerText, stdout.innerHTML);
							// Up arrow.
						} else if (key == 38) {
							if (historyIndex <= history.length - 1) {
								stdout.innerHTML = lastHtml
										+ history[history.length - historyIndex];
								historyIndex++;
							} else {

							}

							// Down arrow.
						} else if (key == 40) {
							if (historyIndex > 0 && historyIndex <= history.length - 1) {
								stdout.innerHTML = lastHtml
										+ history[history.length - historyIndex];
								historyIndex--;
							} else {

							}
						} else if (key == 9) {
							var array = stdout.innerText.split("$");
							var matchStr = array[1].trim();
							var match = tabMatch(matchStr);
							if (match != null) {
								stdout.innerHTML = lastHtml + match;
							}
							// Ctrl+C, Ctrl+D.
						} else if ((key == 67 || key == 68) && e.ctrlKey) {
							if (key == 67)
								this.write('^C');
							this.defaultReturnHandler();
							this._prompt();
						}
					}
					
					function tabMatch(matchStr) {
						if (fileList.length == 0) {
							return null;
						}
						var str = matchStr;
						var start = null;
						var flag = false;
						if (matchStr.indexOf("cd") == 0) {
							str = str.substring(2);
							start = "cd ";
						}
						if (matchStr.indexOf("cat") == 0) {
							str = str.substring(3);
							start = "cat ";
						}
						if (matchStr.indexOf("tail -f") == 0) {
							str = str.substring(7);
							start = "tail -f ";
						}
						for (var i = 0; i < fileList.length; i++) {
							if (fileList[i].indexOf(str.trim()) == 0) {
								return start + fileList[i];
							}
						}
					}
					
					function toggleBlinker(timeout) {
						var blinker = this.div.querySelector('#blinker'), stdout;

						if (blinker) {
							blinker.parentNode.removeChild(blinker);
						} else {
							stdout = stdoutT();
							if (stdout) {
								blinker = document.createElement('span');
								blinker.id = 'blinker';
								blinker.innerHTML = '&#x2588';
								stdout.parentNode.appendChild(blinker);
							}
						}

						if (timeout) {
							setTimeout(function() {
								toggleBlinker(timeout);
							}.bind(this), timeout);
						}
					}

					function stdoutT() {
						return this.div.querySelector('#stdout');
					}

					loadFS();
				});