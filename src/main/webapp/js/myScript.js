jQuery(document).ready(function($){
    let formModal = $('.cd-user-modal'),
        formLogin = formModal.find('#cd-login'),
        formSignup = formModal.find('#cd-signup'),
        formForgotPassword = formModal.find('#cd-reset-password'),
        formModalTab = $('.cd-switcher'),
        tabLogin = formModalTab.children('li').eq(0).children('a'),
        tabSignup = formModalTab.children('li').eq(1).children('a'),
        forgotPasswordLink = formLogin.find('.cd-form-bottom-message a'),
        backToLoginLink = formForgotPassword.find('.cd-form-bottom-message a'),
        mainNav = $('.lex-main-nav');

    //open modal
    mainNav.on('click', function(event){
        $(event.target).is(mainNav) && mainNav.children('ul').toggleClass('is-visible');
    });

    let QI_container = $(".question-image-wrap");
    let Q_img = $("#question-image");
    if ((QI_container.width() / QI_container.height()) < (Q_img.width() / Q_img.height())) {
        QI_container.removeClass('exmpl1');
        QI_container.addClass('exmpl');
    } else {
        QI_container.removeClass('exmpl');
        QI_container.addClass('exmpl1');
    }

    let UI_container = $(".lex-profile-image-wrapper");
    let U_img = $("#lex-profile-image");
    if ((UI_container.width() / UI_container.height()) < (U_img.width() / U_img.height())) {
        U_img.removeClass('lex-profile-image');
        U_img.addClass('lex-profile-image-invert');
    } else {
        U_img.removeClass('lex-profile-image-invert');
        U_img.addClass('lex-profile-image');
    }

    let audio=document.querySelector("audio");
    if(audio != null){
        audio.volume=0.2;
    }

    $('#accept-terms').change(function (){
        if($('#accept-terms')[0].checked){
            $('#create-user-input').prop( "disabled", false);
        } else {
            $('#create-user-input').prop( "disabled", true);
        }

    });
    $('.form-check-input').change(function (){
        $('#answer-submit').prop( "disabled", false);
    })

    $('.lex-quest-play').on('click', ()=>{
        if ($('.lex-quest-play').attr('href')==="#0"){
            login_selected();
        }
    });

    //open sign-up form
    mainNav.on('click', '.cd-signup', signup_selected);
    //open login-form form
    mainNav.on('click', '.cd-signin', login_selected);

    //close modal
    formModal.on('click', function(event){
        if( $(event.target).is(formModal) || $(event.target).is('.cd-close-form') ) {
            formModal.removeClass('is-visible');
        }
    });
    //close modal when clicking the esc keyboard button
    $(document).keyup(function(event){
        if(event.which==='27'){
            formModal.removeClass('is-visible');
        }
    });

    //switch from a tab to another
    formModalTab.on('click', function(event) {
        event.preventDefault();
        ( $(event.target).is( tabLogin ) ) ? login_selected() : signup_selected();
    });

    //hide or show password
    $('.hide-password').on('click', function(){
        let togglePass= $(this),
            passwordField = togglePass.prev('input');

        ( 'password' === passwordField.attr('type') ) ? passwordField.attr('type', 'text') : passwordField.attr('type', 'password');
        ( 'Hide' === togglePass.text() ) ? togglePass.text('Show') : togglePass.text('Hide');
        //focus and move cursor to the end of input field
        passwordField.putCursorAtEnd();
    });

    //show forgot-password form
    forgotPasswordLink.on('click', function(event){
        event.preventDefault();
        forgot_password_selected();
    });

    //back to log-in from the forgot-password form
    backToLoginLink.on('click', function(event){
        event.preventDefault();
        login_selected();
    });

    function login_selected(){
        mainNav.children('ul').removeClass('is-visible');
        formModal.addClass('is-visible');
        formLogin.addClass('is-selected');
        formSignup.removeClass('is-selected');
        formForgotPassword.removeClass('is-selected');
        tabLogin.addClass('selected');
        tabSignup.removeClass('selected');
    }

    function signup_selected(){
        mainNav.children('ul').removeClass('is-visible');
        formModal.addClass('is-visible');
        formLogin.removeClass('is-selected');
        formSignup.addClass('is-selected');
        formForgotPassword.removeClass('is-selected');
        tabLogin.removeClass('selected');
        tabSignup.addClass('selected');
    }

    function forgot_password_selected(){
        formLogin.removeClass('is-selected');
        formSignup.removeClass('is-selected');
        formForgotPassword.addClass('is-selected');
    }

    //REMOVE THIS - it's just to show error messages
    formLogin.find('input[type="submit"]').on('click', function(event){
        event.preventDefault();
        let signingLogin = $("#signin-login");
        let signingPassword = $("#signin-password");
        if (signingLogin.val()==="") {
            signingLogin.toggleClass('has-error').next('span').toggleClass('is-visible');
        } else {
            signingLogin.toggleClass('has-error', false).next('span').toggleClass('is-visible', false);
            if (signingPassword.val() === "") {
                signingPassword.toggleClass('has-error').next('a').next('span').toggleClass('is-visible');
            } else {
                signingPassword.toggleClass('has-error', false).next('a').next('span').toggleClass('is-visible', false);
                loginSubmit($("#login-form"));
            }
        }
    });
    formSignup.find('input[type="submit"]').on('click', function(event){
        event.preventDefault();
        let signupUsername = $("#signup-username");
        let signupLogin = $("#signup-login");
        let signupPassword = $("#signup-password");
        if (signupUsername.val() === "") {
            signupUsername.toggleClass('has-error').next('span').toggleClass('is-visible');
        } else {
            signupUsername.toggleClass('has-error', false).next('span').toggleClass('is-visible', false);
            if (signupLogin.val() === ""){
                signupLogin.toggleClass('has-error').next('span').toggleClass('is-visible');
            } else {
                signupLogin.toggleClass('has-error', false).next('span').toggleClass('is-visible', false);
                if (signupPassword.val() === "") {
                    signupPassword.toggleClass('has-error').next('a').next('span').toggleClass('is-visible');
                } else {
                    signupPassword.toggleClass('has-error', false).next('a').next('span').toggleClass('is-visible', false);
                    signUpSubmit($("#signup-form"));
                }
            }

        }
    });
});


//credits http://css-tricks.com/snippets/jquery/move-cursor-to-end-of-textarea-or-input/
jQuery.fn.putCursorAtEnd = function() {
    return this.each(function() {
        // If this function exists...
        if (this.setSelectionRange) {
            // ... then use it (Doesn't work in IE)
            // Double the length because Opera is inconsistent about whether a carriage return is one character or two. Sigh.
            let len = $(this).val().length * 2;
            this.focus();
            this.setSelectionRange(len, len);
        } else {
            // ... otherwise replace the contents with itself
            // (Doesn't work in Google Chrome)
            $(this).val($(this).val());
        }
    });
};

$('#change-pass-btn').on('click', ()=>{
    $('#change-pass-btn').toggleClass('is-hide');
    $('#change-pass-inputs').toggleClass('is-hide');
    $('#new-pass').prop( "disabled", false);
    $('#repeat-pass').prop( "disabled", false);

});

$('#repeat-pass').keyup(function (){
    let repeatPass = $('#repeat-pass');
    if (!(repeatPass.val() === $('#new-pass').val())){
        repeatPass.css('background', 'red');
        $('#save-edit-btn').prop( "disabled", true);
    } else {
        repeatPass.css('background', 'white');
        $('#save-edit-btn').prop( "disabled", false);
    }
});

$('#edit-user-image-btn').on('click', ()=>{
    $('.lex-image-btn-group').toggleClass('is-hide');
});

$('#edit-user-btn').on('click', ()=>{
    let cells = $('.lex-details-data');

    cells.each((i, cell)=>{
        if (!$(cell).children('input').length > 0) {
            let value = $(cell).text();
            let tdName = $(cell).attr('td-name');

            cell.innerHTML = "";
            const input = createInput(value, tdName);
            cell.appendChild(input);
        } else {
            let inpValue = $(cell).children('input').val();
            $(cell).empty();
            cell.innerHTML = inpValue;
            $('#new-pass').prop( "disabled", true);
            $('#repeat-pass').prop( "disabled", true);
        }
    })

    $('.lex-change-pass-wrap').toggleClass('is-hide');
    let changePassBtn = $('#change-pass-btn');
    if (changePassBtn.css('display') === 'none'){
        changePassBtn.removeClass('is-hide');
        $('#change-pass-inputs').addClass('is-hide');
    }

    $('.lex-confirm-btn-wrap').toggleClass('is-hide');
});

function createInput(currentValue, tdName){
    const input = document.createElement('input');
    input.type = "text";
    input.value = currentValue;
    input.name = tdName;
    input.required = true;
    return input;
}
$('#cancel-edit-btn').on('click', ()=>{
    window.location.href="/user"
})
function loginSubmit(loginForm){
    $.ajax({
        type: 'POST',
        url: 'login',
        data: $(loginForm).serialize(),
        success: () => {
            window.location.href="/home"
            console.log('success login')
        },
        error: () => {
            $('#error-login').removeClass('is-hide');
        }
    });
}

function signUpSubmit(signUpForm){
    $.ajax({
        type: 'POST',
        url: 'user',
        data: $(signUpForm).serialize(),
        success: () => {
            window.location.href="/user"
            console.log('success create')
        },
        error: () => {
            $('#error-submit').removeClass('is-hide');
        }
    });
}

$('#loginForm').submit(function (event) {
    $.ajax({
        type: 'POST',
        url: 'login',
        data: $('#loginForm').serialize(),
        success: () => {
            window.location.href="/home"
            console.log('success login')
        },
        error: () => {
            $('#errorLogin').removeClass('is-hide');
        }
    });

    event.preventDefault();
    event.stopPropagation();
});

function updateUsers(uri, id){
    $.ajax({
        type: 'POST',
        url: `${uri}`,
        data: {role: $(`#user-role-${id}`).val()},
        success: () => {
            window.location.href="/users"
            console.log('success update')
        }
    });
}

function deleteUsers(uri){
    $.ajax({
        url: `${uri}`,
        type: 'POST',
        success: function() {
            window.location.href="/users"
            console.log('success delete')
        }
    });
}





