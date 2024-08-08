function resetForm() {
    document.getElementById('idInput').value = '';
    document.getElementById('nameInput').value = '';
    // document.getElementById('imageInput').value = '';
    document.getElementById('topicInput').value = '';
    document.getElementById('dateInput').value = '';
    document.getElementById('creationDate').value = '';
}

function deleteCourse(button) {
    let id = button.getAttribute("data");
    console.log('Course: ' + id)
    console.log(button)
    Swal.fire({
        title: 'Bạn có chắc chắn xóa không?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3b71ca',
        cancelButtonColor: '#dc4c64',
        confirmButtonText: 'OK',
        cancelButtonText: 'Hủy'
    }).then((result) => {
        if (result.isConfirmed) {
            axios.delete(`/course-api/deleteByIdCourse/${id}`)
                .then(response => {
                    if (response.data.success) {
                        Swal.fire(
                            'Đã xóa!',
                            'Khóa học đã được xóa.',
                            'success'
                        ).then(() => {
                            location.reload();
                        });
                    } else {
                        Swal.fire(
                            'Lỗi!',
                            'Không thể xóa khóa học.',
                            'error'
                        );
                    }
                })
                .catch(error => {
                    Swal.fire(
                        'Lỗi!',
                        'Đã xảy ra lỗi.',
                        'error'
                    );
                });
        }
    });
}

function postCourse() {
    let courseId = document.getElementById('idInput').value;
    let courseName = document.getElementById('nameInput').value;
    let courseImage = document.getElementById('fileInput').value.split('\\')[2];
    let topic = document.getElementById('topicInput').value;
    // let gv = document.getElementById('gvInput').value;
    let creationDate = document.getElementById('dateInput').value;
    let duration = document.getElementById('duration').value;

    let insertCourse = {}
    if (courseId) {
        insertCourse = {
            courseId: courseId,
            courseName: courseName,
            courseImage: courseImage,
            topic: {
                topicId: topic
            },
            creationDate: creationDate,
            videoDuration: duration
        };
    } else {
        insertCourse = {
            courseName: courseName,
            courseImage: courseImage,
            topic: {
                topicId: topic
            },
            creationDate: creationDate,
            videoDuration: duration
        };
    }
    console.table(insertCourse)
    axios.post('/course-api/postCourse', insertCourse)
        .then(response => {
            if (!document.getElementById('idInput').value) {
                if (response.data.success) {
                    uploadFile();
                    Swal.fire({
                        icon: 'success',
                        title: 'Thêm khóa học thành công',
                        text: 'Thông tin khóa học đã được thêm thành công.',
                        timer: 1500
                    }).then(() => {
                        location.reload();
                    });
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Thêm khóa học thất bại',
                        text: 'Đã xảy ra lỗi khi thêm thông tin khóa học. Vui lòng thử lại sau.',
                    });
                }
            } else {
                if (response.data.success) {
                    Swal.fire({
                        icon: 'success',
                        title: 'Cập nhật thông tin thành công',
                        text: 'Thông tin khóa học đã cập nhật thành công.',
                    }).then(() => {
                        location.reload();
                    });
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Cập nhật thông tin thất bại',
                        text: 'Đã xảy ra lỗi khi cập nhật thông tin khóa học. Vui lòng thử lại sau.',
                    });
                }
            }

        })
        .catch(error => {
            console.error('Error:', error);
            Swal.fire({
                icon: 'error',
                title: 'Lỗi',
                text: 'Đã xảy ra lỗi khi gửi yêu cầu. Vui lòng thử lại sau.',
            });
        });
}

function updateCourse(button) {
    document.getElementById('ex1-tab-1').classList.toggle('active')
    document.getElementById('ex1-tab-1').classList.toggle('ripple-surface-primary')
    document.getElementById('ex1-tabs-1').classList.toggle('active')
    document.getElementById('ex1-tabs-1').classList.toggle('show')
    document.getElementById('ex1-tab-2').classList.toggle('active')
    document.getElementById('ex1-tab-2').classList.toggle('ripple-surface-primary')
    document.getElementById('ex1-tabs-2').classList.toggle('active')
    document.getElementById('ex1-tabs-2').classList.toggle('show')

    let tds = button.parentElement.parentElement.getElementsByTagName('td')
    let data = Array.from(tds).map((td) => {
        var tdVar ;
        const attrTd = td.getAttribute('data-value');
        console.log('attr:'+attrTd)
        if(attrTd !== null )
        {
          return tdVar =  attrTd;
        }
        if (td.id === 'img-avatar') {
            let img = td.querySelector('img');
            tdVar =  img ? img.src : '';
        } else {
            tdVar =  td.textContent;
        }
        return tdVar;
    });
    console.table(tds)
    console.table(data)

    let course = {
        courseId: data[0],
        courseName: data[1],
        courseImage: data[2],
        topic: data[3],
        date: data[4],
        duration: data[5]
    }
    fillDataToForm(course)
}

fillDataToForm = (course) => {
    console.log('filldata')
    console.log(course)

    document.getElementById('idInput').value = course.courseId;
    document.getElementById('idInput').focus();
    document.getElementById('nameInput').value = course.courseName;
    document.getElementById('nameInput').focus();
    document.getElementById('imagePreview').src = course.courseImage;

    document.getElementById('topicInput').value = course.topic;
    document.getElementById('dateInput').value = course.date;
    document.getElementById('dateInput').focus();
    document.getElementById('duration').value = course.duration;
    document.getElementById('duration').focus();
}

function uploadFile() {
    const fileInput = document.getElementById('fileInput');
    const file = fileInput.files[0];
    console.log(file)

    const formData = new FormData();
    formData.append('file', file);

    axios.post('/uploadFile/course', formData, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    })
        .then(response => {
            console.log(response.data);
        })
        .catch(error => {
            console.error('There was an error!', error);
        });
}

function val() {
    console.log(document.getElementById("topicInput").value)
}