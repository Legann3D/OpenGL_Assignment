#version 330

in vec4 fragColour;  
in vec4 transformedPosition;
in vec3 fragNormal;

out vec4 finalColor;

void main() {
    
    vec3 encodedNormal = (fragNormal + 1.0) * 0.5; 
    finalColor = vec4(fragColour.rgb * encodedNormal, fragColour.a);
}
