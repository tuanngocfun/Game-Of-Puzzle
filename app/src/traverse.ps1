# Function to print directory and content of each file
function Traverse {
    param (
        [string]$dir,
        [string]$outputFile
    )

    Get-ChildItem -Path $dir -Recurse | ForEach-Object {
        if (-not $_.PSIsContainer) {
            # If it's a file, print the directory and content
            Add-Content -Path $outputFile -Value "Directory: $($_.DirectoryName)"
            Add-Content -Path $outputFile -Value "File: $($_.Name)"
            Add-Content -Path $outputFile -Value "Content:"
            Get-Content -Path $_.FullName | Add-Content -Path $outputFile
            Add-Content -Path $outputFile -Value ""
        }
    }
}

# Get the script's directory
$scriptDir = Split-Path -Parent $MyInvocation.MyCommand.Path
$outputFile = "$scriptDir\output-file.txt"

# Start traversing from the current directory and redirect output to file
Traverse -dir (Get-Location) -outputFile $outputFile
