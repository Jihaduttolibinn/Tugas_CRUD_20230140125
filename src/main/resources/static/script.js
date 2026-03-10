const API_URL = '/ktp';

$(document).ready(function() {
    loadKtp();

    // Form Submission (Add/Update)
    $('#ktp-form').submit(function(e) {
        e.preventDefault();
        
        const id = $('#ktp-id').val();
        const data = {
            nomorKtp: $('#nomorKtp').val(),
            namaLengkap: $('#namaLengkap').val(),
            alamat: $('#alamat').val(),
            tanggalLahir: $('#tanggalLahir').val(),
            jenisKelamin: $('#jenisKelamin').val()
        };

        if (id) {
            updateKtp(id, data);
        } else {
            createKtp(data);
        }
    });

    // Cancel Edit
    $('#btn-cancel').click(function() {
        resetForm();
    });
});

function loadKtp() {
    $.ajax({
        url: API_URL,
        method: 'GET',
        success: function(response) {
            const list = $('#ktp-list');
            list.empty();
            
            if (response.data && response.data.length > 0) {
                response.data.forEach(ktp => {
                    list.append(`
                        <tr>
                            <td><strong>${ktp.nomorKtp}</strong></td>
                            <td>${ktp.namaLengkap}</td>
                            <td><span class="badge">${ktp.jenisKelamin}</span></td>
                            <td>${formatDate(ktp.tanggalLahir)}</td>
                            <td>${ktp.alamat}</td>
                            <td>
                                <button onclick="editKtp(${ktp.id})" class="btn btn-edit">Edit</button>
                                <button onclick="deleteKtp(${ktp.id})" class="btn btn-danger">Hapus</button>
                            </td>
                        </tr>
                    `);
                });
            } else {
                list.append('<tr><td colspan="6" style="text-align: center; padding: 2rem; color: #64748b;">Belum ada data kependudukan.</td></tr>');
            }
        },
        error: function(err) {
            showNotification('Gagal mengambil data: ' + (err.responseJSON ? err.responseJSON.message : 'Unknown error'), 'error');
        }
    });
}

function createKtp(data) {
    $.ajax({
        url: API_URL,
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function(response) {
            showNotification('Data KTP berhasil ditambahkan!', 'success');
            resetForm();
            loadKtp();
        },
        error: function(err) {
            handleError(err);
        }
    });
}

function editKtp(id) {
    $.getJSON(`${API_URL}/${id}`, function(response) {
        const ktp = response.data;
        $('#ktp-id').val(ktp.id);
        $('#nomorKtp').val(ktp.nomorKtp);
        $('#namaLengkap').val(ktp.namaLengkap);
        $('#alamat').val(ktp.alamat);
        $('#tanggalLahir').val(ktp.tanggalLahir);
        $('#jenisKelamin').val(ktp.jenisKelamin);
        
        $('#form-title').text('Edit Data KTP');
        $('#btn-save').text('Update Data');
        $('#btn-cancel').show();
        
        // Scroll to form
        document.querySelector('.form-section').scrollIntoView({ behavior: 'smooth' });
    });
}

function updateKtp(id, data) {
    $.ajax({
        url: `${API_URL}/${id}`,
        method: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function(response) {
            showNotification('Data KTP berhasil diperbarui!', 'success');
            resetForm();
            loadKtp();
        },
        error: function(err) {
            handleError(err);
        }
    });
}

function deleteKtp(id) {
    if (confirm('Apakah Anda yakin ingin menghapus data ini?')) {
        $.ajax({
            url: `${API_URL}/${id}`,
            method: 'DELETE',
            success: function(response) {
                showNotification('Data KTP berhasil dihapus.', 'success');
                loadKtp();
            },
            error: function(err) {
                showNotification('Gagal menghapus data.', 'error');
            }
        });
    }
}

function resetForm() {
    $('#ktp-form')[0].reset();
    $('#ktp-id').val('');
    $('#form-title').text('Tambah Data KTP');
    $('#btn-save').text('Simpan Data');
    $('#btn-cancel').hide();
}

function showNotification(message, type) {
    const $notif = $('#notification');
    $notif.text(message).removeClass('success error').addClass(type).addClass('show');
    
    setTimeout(() => {
        $notif.removeClass('show');
    }, 4000);
}

function handleError(err) {
    let message = 'Terjadi kesalahan.';
    if (err.responseJSON) {
        if (err.responseJSON.data && typeof err.responseJSON.data === 'object') {
            const errors = Object.values(err.responseJSON.data);
            message = errors.join(', ');
        } else {
            message = err.responseJSON.message;
        }
    }
    showNotification(message, 'error');
}

function formatDate(dateStr) {
    const options = { year: 'numeric', month: 'long', day: 'numeric' };
    return new Date(dateStr).toLocaleDateString('id-ID', options);
}
