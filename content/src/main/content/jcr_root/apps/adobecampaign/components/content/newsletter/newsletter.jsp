<%@include file="/apps/adobecampaign/global.jsp" %>

<div id="response">
    <pre style="color: #ff0000"></pre>
</div>
<form id="subscribe">
    <div class = "input_checkbox">
        <input type="checkbox" name="newsletter_campaign" id="technology" value="technology"/>
        <label for="technology">Technology</label>
    </div>
    <div class = "input_checkbox">
        <input type="checkbox" name="newsletter_campaign" id="lifeScience" value="lifescience" />
        <label for="lifeScience">Life Science</label>
    </div>
    <div class = "input_checkbox">
        <input type="checkbox" name="newsletter_campaign" id="science" value="science" />
        <label for="science">Science</label>
    </div>
    <input type="text" name="subscribe_email"/></br></br>

    <button type="submit">Subscribe Now</button>
</form>
<script>
    (function ($) {
        function
                processForm(e) {
            $.ajax({
                url: '/bin/newsletterToSubscribe',
                dataType: 'text',
                type: 'post',
                data: $(this).serialize(),
                success: function (data) {
                    console.log(data);
                    $('#response pre').html(data);
                },
                error: function (jqXhr, textStatus, errorThrown) {
                    console.log(errorThrown);
                }
            });
            e.preventDefault();
        }
        $('#subscribe').submit(processForm);
    })(jQuery);
</script>
